package spring.annotation.jsr;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.inject.Named;

/**
 * 使用JSR250标准注解
 * Resource,PostConstruct,PreDestroy注解
 */
@Service
public class JsrServie {

	/**
	 * Resource注解用于注解变量或get方法
	 * 如果没有显示声明name,默认为属性名或方法名
	 */
	//@Resource
	private JsrDAO jsrDAO;

	@Resource(name = "jsrDAO")
	public void setJsrDAO(JsrDAO jsrDAO) {
		this.jsrDAO = jsrDAO;
	}

	/**
	 * 初始化注解
	 */
	@PostConstruct
	public void init() {
		System.out.println("JsrServie init.");
	}

	/**
	 * 销毁注解
	 */
	@PreDestroy
	public void destroy() {
		System.out.println("JsrServie destroy.");
	}

	public void save() {
		jsrDAO.save();
	}

}
