package servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * web.xml中要定义一个listener属性.
 */
public class InitSpringFactoryListener implements ServletContextListener {
	public InitSpringFactoryListener() {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//这里将加载beans.xml加载到内存中,放到servletcontext中,名称可以随便取,这里取为SpringApplicationContext,
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		arg0.getServletContext().setAttribute("SpringApplicationContext", applicationContext);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}