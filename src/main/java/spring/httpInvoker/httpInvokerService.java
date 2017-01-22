package spring.httpInvoker;

import org.springframework.stereotype.Service;

@Service("httpInvokerService")
public interface HttpInvokerService {
	public String sayHello();
}
