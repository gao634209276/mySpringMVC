package spring.rmi;

import org.springframework.stereotype.Service;

@Service
public class RmiServiceImpl implements RmiService{

	@Override
	public String sayHello() {
		return "Rmi sayHello";
	}
}
