package spring.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import spring.annotation.autowired.injection.service.InjectionService;
import spring.annotation.autowired.collection.BeanInvoker;
import spring.base.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestAutowired extends UnitTestBase {

	public TestAutowired() {
		super("classpath:/annotation/spring-annotation.xml");
	}

	@Test
	public void testAutowired() {
		InjectionService service = super.getBean("injectionServiceImpl");
		service.save("This is autowired.");
	}

	@Test
	public void testMultiBean() {
		BeanInvoker invoker = super.getBean("beanInvoker");
		invoker.say();
	}

}
