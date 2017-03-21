package spring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Bean中需要直接注入Properties配置文件中的值
 * 在server端，需要使用@Component注解为bean，自动注入bean，或者需要在xml中配置bean
 */
@Component
public class Client {
	//当Bean中存在Properties类型的类变量需要以注入的方式初始化
	@Value("#{remoteSettings}")
	private Properties remoteSettings;
	@Value("#{remoteSettings['remote.ip']}")
	public String ip;
	@Value("#{remoteSettings['remote.port']}")
	public String port;
	@Value("#{remoteSettings['remote.serviceName']}")
	public String service;

	private Properties properties;
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public Properties getProperties() {
		return properties;
	}


}
