package spring.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import spring.base.UnitTestBase;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanScope extends UnitTestBase {

	public TestBeanScope() {
		super("classpath*:bean/spring-beanScope.xml");
	}

	/**
	 * 在resources/bean/spring-beanScope.xml中修改
	 * scope="singleton"
	 * scope="prototype"
	 */
	@Test
	public void testSay() {
		// 由于加载spring-beanScope一次,即初始化实例化了一次
		// 这里分别调用两次,观察bean实例的hash值
		say("beanScopeSingleton");
		// singleton的hash相同
		say("beanScopeSingleton");

		// 而prototype的每次都是一个新的hash
		say("beanScopePrototype");
		say("beanScopePrototype");
	}

	public void say(String scopeBean) {
		BeanScope beanScope = super.getBean(scopeBean);
		beanScope.say();
	}

}
