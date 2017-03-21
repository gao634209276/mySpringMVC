package spring.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import service.HelloService;


public class BeanTest {
	private static final Log log = LogFactory.getLog(BeanTest.class);

	/**
	 * ClassPathXmlApplicationContext[只能读放在web-info/classes目录下的配置文件]
	 * classpath:前缀是不需要的,默认就是指项目的classpath路径下面;
	 * 和FileSystemXmlApplicationContext的区别
	 * 如果要使用绝对路径,需要加上file:前缀表示这是绝对路径;
	 * 对于FileSystemXmlApplicationContext:
	 * 默认表示的是两种:
	 * <p>
	 * 1.没有盘符的是项目工作路径,即项目的根目录;
	 * 2.有盘符表示的是文件绝对路径.
	 */
	@Test
	public void testBean() {
		//简单的用ApplicationContext做测试的话,获得spring中定义的Bean实例(对象).可以用:
		// 方法1,使用传统方式去加载beans.xml,每次请求时加载
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		//如果是两个以上:
		applicationContext = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml", "config.xml"});
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml", "config.xml");
		//或者用通配符:
		applicationContext = new ClassPathXmlApplicationContext("classpath:/*.xml");

		// Resource resource = new ClassPathResource("appcontext.xml");
		// BeanFactory factory = new XmlBeanFactory(resource);
		// 用classpath路径
		// ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:appcontext.xml");
		// ApplicationContext factory = new ClassPathXmlApplicationContext("appcontext.xml");

		// ClassPathXmlApplicationContext使用了file前缀是可以使用绝对路径的
		// ApplicationContext factory = new ClassPathXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");
		// 用文件系统的路径,默认指项目的根路径
		// ApplicationContext factory = new FileSystemXmlApplicationContext("src/appcontext.xml");
		// ApplicationContext factory = new FileSystemXmlApplicationContext("webRoot/WEB-INF/appcontext.xml");
		// 使用了classpath:前缀,这样,FileSystemXmlApplicationContext也能够读取classpath下的相对路径
		// ApplicationContext factory = new FileSystemXmlApplicationContext("classpath:appcontext.xml");
		// ApplicationContext factory = new FileSystemXmlApplicationContext("file:F:/workspace/example/src/appcontext.xml");

		// 不加file前缀
		ApplicationContext factory = new FileSystemXmlApplicationContext("E:/workspace/mySpringMVC/src/main/resources/applicationContext.xml");
		HelloService hello = (HelloService) factory.getBean("helloService");
		log.info(hello.sayHello("test"));
	}

}
