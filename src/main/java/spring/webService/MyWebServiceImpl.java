package spring.webService;

import org.springframework.stereotype.Service;

/**
 */
@Service
public class MyWebServiceImpl implements MyWebService {
	@Override
	public String sayHello() {
		return "hello WebService";
	}
}
