package spring.ioc.bean.aware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import spring.base.UnitTestBase;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestAware extends UnitTestBase {

	public TestAware() {
		super("classpath:/ioc/bean/spring-aware.xml");
	}

	/**
	 * 测试Aware感知特性
	 */
	@Test
	public void testAware() {
		//SpringContextUtil ctxUtil = super.getBean("springContextUtil");
		SpringContextUtil ctxUtil = SpringContextUtil.getBean("springContextUtil");

		System.out.println("SpringContextUtil : " + ctxUtil.hashCode());
	}

	@Test
	public void testNameAware() {

		MyBeanNameAware myBeanNameAware = super.getBean("myBeanNameAware");
		System.out.println("myBeanNameAware : "
				+ super.getBean("myBeanNameAware").hashCode());

		// 这里的set不会修改整体的application
		myBeanNameAware.setBeanName("newBeanName");
		//super.getBean("newBeanName");
	}

}
