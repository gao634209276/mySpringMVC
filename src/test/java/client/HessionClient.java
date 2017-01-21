package client;

import com.caucho.hessian.client.HessianProxyFactory;
import spring.hessian.HessianService;

import java.net.MalformedURLException;

public class HessionClient {
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8008/hessian/hessianService";
		HessianProxyFactory factory = new HessianProxyFactory();
		HessianService hessianServer = (HessianService) factory.create(HessianService.class, url);
		String ret = hessianServer.sayHello("Test");
		System.out.println(ret);
	}


}
