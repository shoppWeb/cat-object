package sun.cat.generator.control;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import sun.cat.generator.control.model.ControlModel;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Control模板生成处理工具类 Utils
 * Created by 小小工作者 on 2016/6/12.
 */
public class ControlFreemarker {

    public static final String ControlPath = "F:\\idea16Work\\cat-object\\web\\src\\main\\java\\sun\\cat\\control";

    public static final String CONTROL_FTL_PATH = "F:/idea16Work/cat-object/common/target/classes/sun/cat/generator/control";
    public static Configuration configuration = null;
    public static Template templateControl = null;

    static {
        //1.创建配置实例Cofiguration
        configuration  = new Configuration();
        //2.设置模板文件目录
        try {
            configuration.setDirectoryForTemplateLoading(new File(CONTROL_FTL_PATH));
            //获取模板（templateIService）
            templateControl = configuration.getTemplate("Control.ftl");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("初始化FreemarkerControl失败!!!");
        }
    }

    /**
     * 获取实体的主键 和 类型
     * @param entityPath
     * @return
     * @throws Exception
     */
    public static ControlModel getServiceModel(String entityPath) throws Exception{
        ControlModel controlModel = new ControlModel();
        Class clazz =  Class.forName(entityPath);
        ControlModel.EntityModel entityModel = controlModel.new EntityModel();
        entityModel.setEntityName(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1));
        controlModel.setControl(clazz.getName().substring(clazz.getName().lastIndexOf(".")+1)+"Control");
        //获取所有的属性?
        Field[] fs = clazz.getDeclaredFields();
        for( Field field : fs ){
            if (field.getName().equals("id")){

                entityModel.setEntityIdName("id");
                entityModel.setEntityIdType(field.getType().getName());
                controlModel.setEntity(entityModel);
            }
//            System.err.println(field.getType().getSimpleName());//属性的类型的名字
//            System.err.println(field.getName());//属性的名字+回车
        }
        return controlModel;
    }

    /**
     * 创建control接口
     * @param entityPath
     * @return
     * @throws Exception
     */
    public static void createIService(String entityPath) throws Exception{
        ControlModel controlModel = getServiceModel(entityPath);
        //获取输出流（指定到控制台（标准输出））
        File iServiceFile = new File(ControlPath+File.separator+controlModel.getControl()+".java");
        Writer out = new OutputStreamWriter(new FileOutputStream(iServiceFile));
        //数据与模板合并（数据+模板=输出）
        templateControl.process(controlModel, out);
    }



    @Test
    public void test() throws Exception{
        ControlFreemarker.createIService("sun.cat.entity.User");
    }

}
