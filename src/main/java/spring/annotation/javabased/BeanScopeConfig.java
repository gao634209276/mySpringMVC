package spring.annotation.javabased;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Scope演示
 * Created by Noah on 2017/4/4.
 */
@Configuration
public class BeanScopeConfig {
	/**
	 * proxyMode表示代理模式ScopedProxyMode.TARGET_CLASS为代理类,不是接口
	 */
	@Bean(name = "beanScope")
	//@Scope("prototype")
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public BeanScope beanScope() {
		System.out.println("beanScope() Method run...");
		return new BeanScope();
	}


}
