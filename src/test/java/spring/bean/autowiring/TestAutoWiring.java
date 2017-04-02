package spring.bean.autowiring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import spring.base.UnitTestBase;


@RunWith(BlockJUnit4ClassRunner.class)
public class TestAutoWiring extends UnitTestBase {

	public TestAutoWiring() {
		super("classpath:spring-autoWiring.xml");
	}

	@Test
	public void testSay() {
		AutoWiringService service = super.getBean("autoWiringService");
		service.say(" this is a test");
	}

}
