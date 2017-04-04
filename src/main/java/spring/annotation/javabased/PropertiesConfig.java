package spring.annotation.javabased;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configuration注解声明该Bean通过java注解配置Bean,与xml配置<bean/>效果相同
 * ImportResource表示导入一个xml的ioc容器配置,复用该配置
 * properties-config.xml中配置了property-placeholder,加载annotation/jdbc.properties的配置
 * 那么这里在@Value中引入的自动是 jdbc.properties 中的值
 * Created by Noah on 2017/4/4.
 */
@Configuration
@ImportResource("classpath:/annotation/properties-config.xml")
public class PropertiesConfig {

	/**
	 * Value注解表示为属性复制
	 * ${k}寻找properties文件对应的k,返回对应的v
	 */
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		return new DriverManagerDataSource(url, username, password);
	}

	/**
	 * 配置一个MyDriverManager的Bean,
	 * 最终管理并保存了jdbc.properties的url,username,password的值
	 */
	@Bean
	public MyDriverManager myDriverManager() {
		return new MyDriverManager(url, username, password);
	}
}

class MyDriverManager {

	public MyDriverManager(String url, String userName, String password) {
		System.out.println("url : " + url);
		System.out.println("userName: " + userName);
		System.out.println("password: " + password);
	}
}
