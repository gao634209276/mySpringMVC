package client;

import com.caucho.hessian.client.HessianProxyFactory;
import spring.service.ServiceIf;

import java.net.MalformedURLException;

/**
 * 不适用spring bean注入，直接通过hessian api调用
 */
public class HessionClient {
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8008/remote/hessianService";
		HessianProxyFactory factory = new HessianProxyFactory();
		ServiceIf hessianServer = (ServiceIf) factory.create(ServiceIf.class, url);
		String ret = hessianServer.sayHello();
		System.out.println(ret);
	}
}
