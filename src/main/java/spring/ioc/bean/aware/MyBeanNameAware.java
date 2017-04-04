package spring.ioc.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;


public class MyBeanNameAware implements BeanNameAware, ApplicationContextAware {

	private String beanName;
	private static ApplicationContext applicationContext;

	/**
	 * 由于实现了BeanNameAware
	 *
	 * @param name 初始化时Bean工厂为this Bean创建的name
	 */
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
		System.out.println("MyBeanNameAware : " + name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		MyBeanNameAware.applicationContext = applicationContext;
		System.out.println("setApplicationContext : " + applicationContext.getBean(this.beanName).hashCode());
	}


	/**
	 * ResourceLoader
	 */
	public void resource() throws IOException {
		Resource resource = applicationContext.getResource("annotation/config.xml");
		System.out.println(resource.getFilename());
		System.out.println(resource.contentLength());
	}

}
