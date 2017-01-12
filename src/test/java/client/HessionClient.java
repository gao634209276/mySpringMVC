package client;

import com.caucho.hessian.client.HessianProxyFactory;
import spring.hessian.HessianService;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class HessionClient {
	/**
	 * province:a,b,c
	 * netType:2G,3G,4G
	 * payType:0,1
	 * packageId:
	 * age:-50
	 * netTime:20160501-
	 * netAge:
	 * netUseFrequ:1-4,10-
	 * mobUseFrequ:4,
	 */
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8080/hessian/hessianService";
		//String url = "http://10.40.33.12:8180/hessianService/hessian/hessianService";
		HessianProxyFactory factory = new HessianProxyFactory();
		HessianService hessianServer = (HessianService) factory.create(HessianService.class, url);
		//String ret = hessianServer.sayHello("Test");
		//System.out.println(ret);
		test(hessianServer);
	}

	public static void test(HessianService hessianServer) {
		Map<String, String> lable = new HashMap<>();
		// 1.age,eg:0-30
		lable.put("age", "-30");
		// 2,netTime,eg: 20110202-20150505:
		//lable.put("netTime", "20160101-20170101");
		// 3,netAge,eg: 5-10
		lable.put("netAge","-10");
		// 4,netType,eg:2G
		lable.put("netType", "3G");
		// 5,payType,eg:0
		// 6,packageId,eg:
		//lable.put("packageId", "");
		// 7,province,eg:
		// 030 ,088 ,087 ,013 ,031 ,075 ,081 ,034 ,019 ,097 ,071 ,011 ,076
		// 051 ,089 ,086 ,038 ,090 ,070 ,018 ,059 ,010 ,036 ,050 ,079 ,083
		// 017 ,085 ,084 ,074 ,091
		lable.put("province", "030,088,087,013");

		// 1,netUseFrequ
		//lable.put("netUseFrequ", "-4,10-");
		// 2,mobUseFrequ
		//lable.put("mobUseFrequ", "1-,10-12");
		hessianServer.queryResult("test", lable, "1", "");
	}
}
