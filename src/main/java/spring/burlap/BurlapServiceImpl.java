package spring.burlap;

import org.springframework.stereotype.Service;

/**
 */
@Service("BurlapServiceImpl")
public class BurlapServiceImpl implements BurlapService {
	@Override
	public String sayHello(String username) {
		return  "Hello " + username;
	}
}
