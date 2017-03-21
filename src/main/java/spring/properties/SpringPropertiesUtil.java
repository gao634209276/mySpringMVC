package spring.properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SpringPropertiesUtil extends PropertyPlaceholderConfigurer {

	private Map<String, String> propertiesMap;
	// Default as in PropertyPlaceholderConfigurer
	private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;

	@Override
	public void setSystemPropertiesMode(int systemPropertiesMode) {
		super.setSystemPropertiesMode(systemPropertiesMode);
		springSystemPropertiesMode = systemPropertiesMode;
	}

	/**
	 * 自定义实现部分
	 * 将通过spring读取props保存到自己的map中
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);
		propertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			//String valueStr = props.getProperty(valueStr);
			String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
			System.out.println(keyStr + " " + valueStr);
			propertiesMap.put(keyStr, valueStr);
		}
	}


	public String getOrElse(String name, String defaultValue) {

		for (String key : propertiesMap.keySet()) {
			System.out.println(key + " " + propertiesMap.get(key));
		}
		String value = propertiesMap.get(name);
		if (null == value || value.isEmpty()) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 通过application中获取bean实例
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SpringPropertiesUtil properties = (SpringPropertiesUtil) context.getBean("myPropertyConfigurer");
		System.out.println(properties.propertiesMap);
	}
}
