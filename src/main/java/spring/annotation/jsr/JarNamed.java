package spring.annotation.jsr;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 使用JSR330标准注解
 * Named,Inject注解
 * Created by Noah on 2017/4/4.
 */
@Named
public class JarNamed {

	//@Inject
	private JsrDAO jsrDAO;

	@Inject
	public void setJsrDAO(@Named("jsrDAO") JsrDAO jsrDAO) {
		this.jsrDAO = jsrDAO;
	}

	public void save() {
		jsrDAO.save();
	}

}
