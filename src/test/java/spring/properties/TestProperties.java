package spring.properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProperties {
	public static void main(String[] args) {
		//System.out.println(ip);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Client client = (Client) context.getBean("client");
		System.out.println(client.ip);

	}
}
