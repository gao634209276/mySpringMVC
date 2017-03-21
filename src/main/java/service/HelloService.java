package service;

import org.springframework.stereotype.Service;

@Service
public interface HelloService {
	public String sayHello(String name);
}
