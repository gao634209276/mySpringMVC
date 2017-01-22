package spring.burlap;

import org.springframework.stereotype.Service;

/**
 */
@Service("BurlapService")
public interface BurlapService {
	public String sayHello(String username);
}

