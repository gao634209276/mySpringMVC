package spring.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import spring.base.UnitTestBase;
import spring.annotation.jsr.JsrServie;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestJsr extends UnitTestBase {

	public TestJsr() {
		super("classpath*:/annotation/spring-annotation.xml");
	}

	@Test
	public void testSave() {
		JsrServie service = getBean("jsrServie");
		service.save();
	}

}
