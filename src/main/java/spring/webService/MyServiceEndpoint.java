package spring.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebMethod;
import javax.jws.WebService;

@Service
@WebService(serviceName = "testMyService")
public class MyServiceEndpoint extends SpringBeanAutowiringSupport {

	@Qualifier("myWebServiceImpl")
	@Autowired
	MyWebService myService;

	@WebMethod
	public String sayHello() {
		return myService.sayHello();
	}
}
