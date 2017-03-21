package webService;

import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Web服务的接口称为SEI,我们首先要用定义一个接口MyWebService
 * 使用@WebService注解修饰接口名，使用@WebParam修饰需要对外发布的方法，
 */
@Service
@WebService
public interface MyWebService {
	@WebMethod
	public String sayHello(String name);
}
