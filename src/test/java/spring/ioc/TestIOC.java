package spring.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class TestIOC {
	@Test
	public void testIOCBase() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:/ioc/ioc-set.xml");
		//获取容器的一个实例
		HelloBean hb = (HelloBean) ac.getBean("helloBean");
		System.out.println(hb.sayHello());
	}

	@Test
	public void testIOCObj() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:/ioc/ioc-set.xml");
		SomeBean sb = (SomeBean) ac.getBean("someBean");
		sb.printInfo();
	}

	@Test
	public void testIOCList() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:/ioc/ioc-set.xml");
		ListBean lb = (ListBean) ac.getBean("listBean");
		lb.printInfo();
	}
}
