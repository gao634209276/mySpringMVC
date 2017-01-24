package spring.service;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements ServiceIf {

	private static Log log = LogFactory.getLog(ServiceImpl.class);

	@Override
	public String sayHello() {
		log.info("Hello");
		return "Hello";
	}
}
