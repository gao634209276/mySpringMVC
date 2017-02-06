package client;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.service.HelloService;
import spring.webService.MyServiceEndpoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

public class TestClient {

	private ApplicationContext context;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		// 第二种方式
		/*GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:applicationContext.xml");
		ctx.refresh();*/
	}

	/**
	 * RMI虽然简单高效，但使用RMI会存在一些问题，
	 * 比如java序列化的版本问题或者防火墙问题(RMI不是基于HTTP的)
	 */
	@Test
	public void testRmi() {
		HelloService service = (HelloService) context.getBean("rmiProxyFactoryBean");
		System.out.println(service.sayHello("world"));
	}

	/**
	 * 基于http的rmi。。
	 * HttpInvoker是spring的一部分，只能在spring应用中使用
	 */
	@Test
	public void testHttpInvoker() {
		HelloService httpInvoke = context.getBean("httpInvokerService", HelloService.class);
		httpInvoke.sayHello("world");
	}

	/**
	 * Hessian是binary transport protocol，但与RMI不同的是他不是java序列化对象，
	 * 通过私有化的序列化，所以他可以和其他语言的程序通信，比如C++、C#、Python、Ruby什么的。
	 */
	@Test
	public void testHessian() {
		HelloService service = context.getBean(HelloService.class);
		service.sayHello("world");
	}

	/**
	 * Burlap是基于XML的，自然也可以支持很多不同的语言。
	 * 当然，同样地传输内容下，XML的传输量会大一些。
	 * 如果要说有什么好处的话也只有可读性了。
	 */
	@Test
	public void testBurlap() {
		HelloService service = context.getBean(HelloService.class);
		service.sayHello("world");
	}

	@Test
	public void testWebService() {
		// 发布
		Endpoint t = Endpoint.publish("http://localhost:8080/myservices", (MyServiceEndpoint) context.getBean(MyServiceEndpoint.class));
		// 调用

		Service service = Service.create(new QName("http://endpoint.king.pac/", "testMyService"));
		QName q = new QName("http://endpoint.king.pac/", "MyServiceEndpointPort");

	}
}
