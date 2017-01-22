package client;

import com.caucho.hessian.client.HessianProxyFactory;
import spring.hessian.HessianService;

import java.net.MalformedURLException;

/**
 * 不适用spring bean注入，直接通过hessian api调用
 */
public class HessionClient {
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8008/remote/hessianService";
		HessianProxyFactory factory = new HessianProxyFactory();
		HessianService hessianServer = (HessianService) factory.create(HessianService.class, url);
		String ret = hessianServer.sayHello("Test");
		System.out.println(ret);
	}
}
