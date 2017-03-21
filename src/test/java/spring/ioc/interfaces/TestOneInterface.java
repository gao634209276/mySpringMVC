package spring.ioc.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import spring.base.UnitTestBase;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestOneInterface extends UnitTestBase {

	public TestOneInterface() {
		super("classpath*:spring-ioc.xml");
	}

	@Test
	public void testSay() {
		OneInterface oneInterface = super.getBean("oneInterface");
		oneInterface.say("This is a test.");
	}

}
