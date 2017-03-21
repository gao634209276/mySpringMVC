package service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	private static Log log = LogFactory.getLog(HelloServiceImpl.class);

	@Override
	public String sayHello(String name) {
		log.info("Hello " + name);
		return "Hello " + name;
	}
}
