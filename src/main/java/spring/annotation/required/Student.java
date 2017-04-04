package spring.annotation.required;

import org.springframework.beans.factory.annotation.Required;


/**
 * 创建一个Student的POJO类,但这里为了演示Required,我们在ioc容器中配置做为一个bean
 * 并在bean属性中设置age和name
 * See:
 * Created by Noah on 2017/4/4.
 */
public class Student {
	private Integer age;
	private String name;

	@Required
	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}


	/**
	 * 通过自定义的Required,功能与Required相同
	 */
	//@Required
	@CustomRequired
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}