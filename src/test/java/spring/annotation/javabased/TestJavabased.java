package spring.annotation.javabased;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import spring.base.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestJavabased extends UnitTestBase {

	public TestJavabased() {
		super("classpath*:annotation/spring-annotation.xml");
	}

	@Test
	public void test() {
		BeanStore store = super.getBean("stringStore");
		System.out.println(store.getClass().getName());
	}

	@Test
	public void testMyDriverManager() {
		MyDriverManager manager = super.getBean("myDriverManager");
		System.out.println(manager.getClass().getName());
	}

	@Test
	public void testScope() {
		BeanStore store = super.getBean("beanScope");
		System.out.println(store.hashCode());

		store = super.getBean("beanScope");
		System.out.println(store.hashCode());
	}

	@Test
	public void testG() {
		StringStore store = super.getBean("stringStoreTest");
	}

}
