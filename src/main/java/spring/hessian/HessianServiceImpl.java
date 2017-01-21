package spring.hessian;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;


@Service
public class HessianServiceImpl implements HessianService {
	private static Log log = LogFactory.getLog(HessianServiceImpl.class);

	public String sayHello(String username) {

		log.info("Hello " + username);
		return "Hello " + username;
	}

}
