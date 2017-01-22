package spring.httpInvoker;

import org.springframework.stereotype.Service;

/**
 */
@Service("httpInvokerImpl")
public class HttpInvokerImpl implements HttpInvokerService {
	@Override
	public String sayHello() {
		return "Hello Stevex, I am invoked by Spring HTTP Invoker!";
	}
}
