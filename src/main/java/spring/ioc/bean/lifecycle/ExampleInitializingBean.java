package spring.ioc.bean.lifecycle;

import org.springframework.beans.factory.InitializingBean;

public class ExampleInitializingBean implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		//do something
	}

}
