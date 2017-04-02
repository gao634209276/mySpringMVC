package spring.bean.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 参考applicationContext.txt
 * Spring初始化时，会通过setApplicationContext方法将ApplicationContext对象注入。
 * 全局的通用获取appContext，避免重复加载和初始化
 * 需要设置：default-lazy-init="false"
 * Created by Noah on 2017/4/2.
 */
public class SpringContextUtil implements ApplicationContextAware {
	// Spring应用上下文环境
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 *
	 * @param applicationContext 该类使用的上下文对象
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}


	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象
	 *
	 * @param name bean的name
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
}