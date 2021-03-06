package spring.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import spring.base.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanAnnotation extends UnitTestBase {
	
	public TestBeanAnnotation() {
		super("classpath*:/annotation/spring-annotation.xml");
	}
	
	@Test
	public void testSay() {
		BeanAnnotation bean = super.getBean("beanAnnotation");
		bean.say("This is test.");
		
		bean = super.getBean("ioc/bean");
		bean.say("This is test.");
	}
	
	@Test
	public void testScpoe() {
		BeanAnnotation bean = super.getBean("beanAnnotation");
		bean.myHashCode();
		
		bean = super.getBean("beanAnnotation");
		bean.myHashCode();
	}
	
}
