package sun.cat.test;

/**
 * 注解修饰的类
 */
@CustomizeComponent
public class ScanClass1 {

	public void print() {
		System.out.println("scanClass1");
	}


	public static void main(String[] a){

		DemoBean demoBean1 = new DemoBean("小明");
		DemoBean demoBean2 = new DemoBean("孙海龙");
		System.err.println("demo1原始=="+demoBean1+"=="+demoBean1.hashCode());
		System.err.println("demo2原始=="+demoBean2+"=="+demoBean2.hashCode());

		genghuan(demoBean1 , demoBean2);

		System.err.println("demo1===="+demoBean1.getName());
		System.err.println("demo2===="+demoBean2.getName());
	}

//// TODO: 2016/7/14  问题描述  hashCode是指的内存地址吗   怎么判断两个对象时候是否是同一个 

	public static void genghuan(DemoBean deamBean, DemoBean demoBean1) {
		System.err.println("demo1函数中=="+deamBean+"=="+deamBean.hashCode());
		System.err.println("demo2函数中=="+demoBean1+"=="+demoBean1.hashCode());

		DemoBean demoBean2 = demoBean1;

		demoBean1 = deamBean;
		deamBean = demoBean2;

		System.err.println("demo1函数中===="+deamBean.getName());
		System.err.println("demo2函数中===="+demoBean1.getName());

	}


}

/**
 * 测试使用的Bean类
 */
class DemoBean{

	private String name;

	public DemoBean(String name) {
		this.name = name;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
