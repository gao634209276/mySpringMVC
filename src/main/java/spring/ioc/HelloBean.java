package spring.ioc;

/**
 * 通过依赖注入bean，在test测试中测试
 *  see: ioc/ioc-set.xml
 *  see: TestIOC
 *  see: ioc/1ioc.txt
 */
public class HelloBean {

	private String name;
	private int age;

	public String sayHello() {
		return "hello " + name + ",your age is " + age;
	}

	public HelloBean() {
		super();
	}

	public HelloBean(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}