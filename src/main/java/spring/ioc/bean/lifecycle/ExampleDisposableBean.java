package spring.ioc.bean.lifecycle;

import org.springframework.beans.factory.DisposableBean;

public class ExampleDisposableBean implements DisposableBean {

	@Override
	public void destroy() throws Exception {
		//do something
	}
}
