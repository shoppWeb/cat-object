package sun.cat.utils.properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class PropertiesUtil {
	private static final Logger _logger = LogManager.getLogger();
	private static Map<String, Properties> propertiesMap = new ConcurrentHashMap<>();

	/**
	 *
	 * @param propertyFileName
	 * @param propertyKey
	 * @return
	 */
	public static String getPropertyValue(String propertyFileName, String propertyKey) {
		if (!propertiesMap.containsKey(propertyFileName)) {
			Properties properties = new Properties();
			String path = PropertiesUtil.class.getClassLoader().getResource(propertyFileName).getPath();
			try {
				FileInputStream stream = new FileInputStream(path);
				properties.load(stream);
			} catch (Exception e) {
				_logger.info(e.toString());
			}
			propertiesMap.put(propertyFileName, properties);
		}
		return propertiesMap.get(propertyFileName).getProperty(propertyKey).trim();
	}

	/**
	 * 更新或插入
	 * @param propertyFileName
	 * @param keyname
	 * @param keyvalue
	 */
	public static void updateProperties(String propertyFileName, String keyname,String keyvalue) {
		try {
			String path = PropertiesUtil.class.getClassLoader().getResource(propertyFileName).getPath();
			Properties props = new Properties();
			props.load(new FileInputStream(path));
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(path);
			props.setProperty(keyname, keyvalue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, "Update '" + keyname + "' value");
			propertiesMap.remove(keyname);
			fos.close();
		} catch (IOException e) {
			_logger.error("属性文件更新错误");
			_logger.error(e);

		}
	}

	/**
	 * 获得系统属性集
	 * @return
	 */
	public static Properties getProperties(){
		Properties props=System.getProperties(); //获得系统属性集
		return props;
	}

	/**
	 * 获取操作系统名称 
	 * @return
	 */
	public static String getOsName(){
		String osName=getProperties().getProperty("os.name");//操作系统名称 
		return osName;
	}
}
