package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.HelloService;

/**
 * 这里要注意的是实现spring管理Bean的三种方式
 * 第一种:最传统的方式,同时也是效率最低的一种,
 * 因为,每次发一个请求都要重新加载一次,而且对于不同的Servlet的要每个都去加载,会大大降低效率.
 * 第二种:使用监听器来实现加载beans.xml,每次项目启动的时候加载一次就可以了.这样大提高了效率.
 * See: {@link InitSpringFactoryListener}.
 * 第三种:针对第二种方法,其实spring中已经封装好了一种监听器,人工去配置即可,原理和第二种方法一致.
 * 使用spring自带的监听器,其默认加载的是WEB-INF下的applicationContext.xml
 *
 * @see org.springframework.web.context.ContextLoaderListener
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 2801654413247618244L;
	private HelloService hello;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//方法1,使用传统方式去加载beans.xml,每次请求时加载
		//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

		//方法2,使用监听器的方式加载beans.xml,在一启动的时候就加载监听器,避免多次加载,提高效率
		//ApplicationContext applicationContext  = (ApplicationContext) this.getServletContext().getAttribute("SpringApplicationContext");

		//方法3,使用spring自带的监听器去加载beans.xml
		//ApplicationContext applicationContext  = (ApplicationContext) this.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//使用webapplicationcontextutils这个工具类可以很方便的获取ApplicationContext,只需要传入servletContext
		ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());

		hello = applicationContext.getBean(HelloService.class);
		String sayHi = hello.sayHello("");
		System.err.println("sayHi:" + sayHi);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().write("<h2>" + sayHi + "</h2>");
	}
}

