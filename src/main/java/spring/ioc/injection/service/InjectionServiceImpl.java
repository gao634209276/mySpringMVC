package spring.ioc.injection.service;

import spring.ioc.injection.dao.InjectionDAO;

/**
 * Service层实现,
 * 这里通过两种方式注入DAO层实例Bean,
 * 在业务实现中结合业务处理,并调用DAO层的save方法
 */
public class InjectionServiceImpl implements InjectionService {

	private InjectionDAO injectionDAO;

	public InjectionServiceImpl() {
	}

	//构造器注入
	public InjectionServiceImpl(InjectionDAO injectionDAO) {
		this.injectionDAO = injectionDAO;
	}

	//设值注入
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
