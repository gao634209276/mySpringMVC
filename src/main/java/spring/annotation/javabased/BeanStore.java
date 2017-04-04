package spring.annotation.javabased;

/**
 * 关于泛型在泛型的自动装配进行说明,在@Bean和@Scope讲解中没有意义,不必关心
 * <p>
 * 泛型的自动装配
 * 将接口定义为泛型,分别在实现类中定义两种不同的泛型实现
 */
public interface BeanStore<T> {
}

class StringStore implements BeanStore<String> {

	public void init() {
		System.out.println("This is init.");
	}

	public void destroy() {
		System.out.println("This is destroy.");
	}

}

class IntegerStore implements BeanStore<Integer> {

}

