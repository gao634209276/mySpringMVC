package webService;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * WEB服务接口的实现类称为SIB，
 * 注解@Component用于Spring查找组件
 */
@Service
@Component
@WebService(serviceName = "MyWebServiceImpl", endpointInterface = "webService.MyServiceEndpoint")
public class MyWebServiceImpl implements MyWebService {
	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}
}
