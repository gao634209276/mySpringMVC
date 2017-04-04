package spring.annotation.autowired.injection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.annotation.autowired.injection.dao.InjectionDAO;

@Service
public class InjectionServiceImpl implements InjectionService {

	/**
	 * Autowired可直接用于注解属性,但这一般不建议
	 */
	//@Autowired
	private InjectionDAO injectionDAO;

	/**
	 * 如果单从属性或者setter方法注解@Autowired,
	 * 而又有明确声明了含参构造器且没有构造注入时,一定再明确声明一个无参构造器
	 */
	public InjectionServiceImpl() {
	}

	/**
	 * Autowired构造入住
	 * 每个类只能有一个构造器被标记为required=true
	 * 默认(required = true)
	 */
	// @Autowired
	public InjectionServiceImpl(InjectionDAO injectionDAO) {
		this.injectionDAO = injectionDAO;
	}

	/**
	 * Autowired设值注入
	 * 默认情况下,如果因为找不到合适的bean将会导致autowiring失败抛异常,
	 * 可以通过配置(required = false)避免保存
	 * 如果必须保证装载,可以通过@Required注解替换
	 */
	@Autowired(required = false)
	//@Required
	public void setInjectionDAO(InjectionDAO injectionDAO) {
		this.injectionDAO = injectionDAO;
	}


	public void save(String arg) {
		//模拟业务操作
		System.out.println("Service接收参数：" + arg);
		arg = arg + ":" + this.hashCode();
		injectionDAO.save(arg);
	}

}
