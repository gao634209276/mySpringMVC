package spring.annotation.javabased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
public class BeanConfig {
	///-----------------@Bean和@Scope-----------------------------///
	///----------------------------------------------------------///
	/**
	 * Bean注解默认使用方法名称(stringStore)获取,而不是返回值类名的Bean名称
	 * 可以通过@Bean(name="stringStore")明确配置
	 * 对应的init/destroyMethod应该写在返回类型StringStore内部
	 */
	@Bean(name = "stringStore", initMethod = "init", destroyMethod = "destroy")
	@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public StringStore stringStore() {
		return new StringStore();
	}

	@Bean
	public IntegerStore integerStore() {
		return new IntegerStore();
	}


	///----------------泛型@Autowired-----------------------------///
	////---------------------------------------------------------///
	/**
	 * 自动装载属性,BeanStore的String泛型实例s1
	 */
	@Autowired
	private BeanStore<String> s1;

	/**
	 * 自动装载属性,BeanStore的Integer泛型实例s1
	 */
	@Autowired
	private BeanStore<Integer> s2;

	/**
	 * 如果在s2的基础向再配置一个类型为IntegerStore的Bean
	 * 那么方法中泛型为Integer的属性s2就会指定两个Bean,此时Bean将会保存/抛异常
	 */
	/*@Bean(name = "stringStoreTest")
	public BeanStore stringStoreTest() {
		System.out.println("s1 : " + s1.getClass().getName());
		System.out.println("s2 : " + s2.getClass().getName());
		return new StringStore();
	}*/

}
