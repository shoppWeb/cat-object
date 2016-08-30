package sun.cat.common.model;

/**
 * Hibernate SQL 参数通用传输对象
 * 
 */
public class ParameterKeyValue {

	/**
	 * 有参构造
	 * @param key 参数名
	 * @param value 参数值
	 */
	public ParameterKeyValue(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	private String key; // 参数名(键)
	private Object value;// 参数值

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
