package spring.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import spring.annotation.required.Student;
import spring.base.UnitTestBase;

/**
 * 测试Required
 * Created by Noah on 2017/4/4.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestRequired extends UnitTestBase {
	public TestRequired() {
		super("classpath*:/annotation/spring-required.xml");
	}

	@Test
	public void testRequired() {
		Student student = getBean("student");
		System.out.println("Name : " + student.getName());
		System.out.println("Age : " + student.getAge());
	}
}
