package spring.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class MyBeanNameAware implements BeanNameAware, ApplicationContextAware {

	private String beanName;

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
		System.out.println("setApplicationContext : " + applicationContext.getBean(this.beanName).hashCode());
	}

}
