package sun.cat.generator.service;

import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import sun.cat.generator.service.model.ServiceModel;

import java.io.*;
import java.lang.reflect.Field;

/**
 * service模板生成处理工具类 Utils
 * Created by 小小工作者 on 2016/6/12.
 */
public class ServiceFreemarker {

    public static final String ServicePath = "F:\\idea16Work\\cat-object\\services\\src\\main\\java\\sun\\cat\\service";

    public static final String SERVICE_FTL_PATH = "F:/idea16Work/cat-object/common/target/classes/sun/cat/generator/service/";
    public static Configuration configuration = null;
    public static Template templateIService = null;
    public static Template templateServiceImpl = null;

    static {
        //1.创建配置实例Cofiguration
        configuration  = new Configuration();
        //2.设置模板文件目录
        try {
            configuration.setDirectoryForTemplateLoading(new File(SERVICE_FTL_PATH));
            //获取模板（templateIService）
            templateIService = configuration.getTemplate("IService.ftl");
            templateServiceImpl = configuration.getTemplate("Service.ftl");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("初始化FreemarkerService失败!!!");
        }
    }

    /**
     * 获取实体的主键 和 类型
     * @param entityPath
     * @return
     * @throws Exception
     */
    public static ServiceModel getServiceModel(String entityPath) throws Exception{
        ServiceModel serviceModel = new ServiceModel();
        Class clazz =  Class.forName(entityPath);
        ServiceModel.EntityModel entityModel = serviceModel.new EntityModel();
        entityModel.setEntityName(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1));
        serviceModel.setService(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)+"Service");
        //获取所有的属性?
        Field[] fs = clazz.getDeclaredFields();
        for( Field field : fs ){
            if (field.getName().equals("id")){

                entityModel.setEntityIdName("id");
                entityModel.setEntityIdType(field.getType().getName());
                serviceModel.setEntity(entityModel);
            }
//            System.err.println(field.getType().getSimpleName());//属性的类型的名字
//            System.err.println(field.getName());//属性的名字+回车
        }
        return serviceModel;
    }

    /**
     * 创建service接口
     * @param entityPath
     * @return
     * @throws Exception
     */
    public static void createIService(String entityPath) throws Exception{
        ServiceModel serviceModel = getServiceModel(entityPath);
        //获取输出流（指定到控制台（标准输出））
        File iServiceFile = new File(ServicePath+File.separator+"I"+serviceModel.getService()+".java");
        Writer out = new OutputStreamWriter(new FileOutputStream(iServiceFile));
        //数据与模板合并（数据+模板=输出）
        templateIService.process(serviceModel, out);
    }
    /**
     * 创建service接口
     * @param entityPath
     * @return
     * @throws Exception
     */
    public static void createServiceImpl(String entityPath) throws Exception{
            ServiceModel serviceModel = getServiceModel(entityPath);
            //获取输出流（指定到控制台（标准输出））
            File iServiceFile = new File(ServicePath+File.separator+"impl"+ File.separator + serviceModel.getService() + ".java");
            Writer out = new OutputStreamWriter(new FileOutputStream(iServiceFile));
            //数据与模板合并（数据+模板=输出）
            templateServiceImpl.process(serviceModel, out);

    }

    public static boolean createService(String entityPath) throws Exception{
        try{
            ServiceFreemarker.createIService(entityPath);
            ServiceFreemarker.createServiceImpl(entityPath);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Test
    public void test() throws Exception{
        ServiceFreemarker.createService("sun.cat.entity.User");
    }

}
