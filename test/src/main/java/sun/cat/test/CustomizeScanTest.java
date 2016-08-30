package sun.cat.test;
/**
 * Copyright 2014-2015, NetEase, Inc. All Rights Reserved.
 * Date: 16/1/22
 */

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * test for customer
 * 1 BeanFactoryPostProcessor after bean factory is created,scan and modify bean definition
 * 2 BeanDefinition , bean class , if a basic class, auto ,else if a factory bean ,create by factory bean
 * 3 FactoryBean , create bean
 * 4 Scan ,basic scan
 *
 */
@Configuration
public class CustomizeScanTest {

	/**
	 * BeanScannerConfigurer用于嵌入到Spring的加载过程的中，这里用到了BeanFactoryPostProcessor 和 ApplicationContextAware。
	 * Spring提供了一些的接口使程序可以嵌入Spring的加载过程。这个类中的继承ApplicationContextAware接口，
	 * Spring会读取ApplicationContextAware类型的的JavaBean，
	 * 并调用setApplicationContext(ApplicationContext applicationContext)传入Spring的applicationContext。
	 同样继承BeanFactoryPostProcessor接口，Spring会在BeanFactory的相关处理完成后调用postProcessBeanFactory方法，进行定制的功能。
	 */
	@Component
	public static class BeanScannerConfigurer implements BeanFactoryPostProcessor, ApplicationContextAware {

		private ApplicationContext applicationContext;

		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext;
		}

		public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
			Scanner scanner = new Scanner((BeanDefinitionRegistry) beanFactory);
			scanner.setResourceLoader(this.applicationContext);
			scanner.scan("sun");
		}
	}

	/**
	 * Scanner继承的ClassPathBeanDefinitionScanner是Spring内置的Bean定义的扫描器
	 * includeFilter里定义了类的过滤器，newAnnotationTypeFilter(sun.cat.test.CustomizeComponent.class)
	 * 表示只取被CustomizeComponent修饰的类。
	 doScan里扫面了包底下的读取道德BeanDefinitionHolder，自定义GenericBeanDefinition相关功能
	 */
	public final static class Scanner extends ClassPathBeanDefinitionScanner {

		public Scanner(BeanDefinitionRegistry registry) {
			super(registry);
		}

		public void registerDefaultFilters() {
			this.addIncludeFilter(new AnnotationTypeFilter(CustomizeComponent.class));
		}

		public Set<BeanDefinitionHolder> doScan(String...basePackages) {
			Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
			for (BeanDefinitionHolder holder : beanDefinitions) {
				GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
				definition.getPropertyValues().add("innerClassName", definition.getBeanClassName());
				definition.setBeanClass(FactoryBeanTest.class);
			}
			return beanDefinitions;
		}

		public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
			return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
					.hasAnnotation(CustomizeComponent.class.getName());
		}

	}

	/**
	 * 普通的JavaBean是直接使用类的实例，但是如果一个Bean继承了这个借口，就可以通过getObject()方法来自定义实例的内容，
	 * 在FactoryBeanTest的getObject()就通过代理了原始类的方法，自定义类的方法
	 * @param <T>
     */
	public static class FactoryBeanTest<T> implements InitializingBean, FactoryBean<T> {

		private String innerClassName;

		public void setInnerClassName(String innerClassName) {
			this.innerClassName = innerClassName;
		}

		public T getObject() throws Exception {
			Class innerClass = Class.forName(innerClassName);
			if (innerClass.isInterface()) {
				return (T) InterfaceProxy.newInstance(innerClass);
			} else {
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(innerClass);
				enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
				enhancer.setCallback(new MethodInterceptorImpl());
				return (T) enhancer.create();
			}
		}

		public Class<?> getObjectType() {
			try {
				return Class.forName(innerClassName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}

		public boolean isSingleton() {
			return true;
		}

		public void afterPropertiesSet() throws Exception {

		}
	}

	public static class InterfaceProxy implements InvocationHandler {

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("ObjectProxy execute:" + method.getName());
			return method.invoke(proxy, args);
		}

		public static <T> T newInstance(Class<T> innerInterface) {
			ClassLoader classLoader = innerInterface.getClassLoader();
			Class[] interfaces = new Class[] { innerInterface };
			InterfaceProxy proxy = new InterfaceProxy();
			return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
		}
	}

	public static class MethodInterceptorImpl implements MethodInterceptor {

		public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
			System.out.println("MethodInterceptorImpl:" + method.getName());
			return methodProxy.invokeSuper(o, objects);
		}
	}

}
