package client;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.demo.LoginResource;

public class TestClient {
	@Test
	public void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-applicationContext.xml");
		LoginResource loginResource = (LoginResource) applicationContext.getBean("loginResource");
		loginResource.login();
	}
}
