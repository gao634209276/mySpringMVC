package spring.client;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.service.HelloService;

public class DubboClient {
	ClassPathXmlApplicationContext context;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("classpath:rpc/dubboconsumer.xml");
	}

	@Test
	public void testDubbo() {
		HelloService service = context.getBean(HelloService.class);

		System.out.println(service.sayHello("world"));

	}
}
