package spring.hessian;

import org.springframework.stereotype.Service;

@Service("hessianService")
public interface HessianService {
	public String sayHello(String username);
}
