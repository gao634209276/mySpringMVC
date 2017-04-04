package spring.annotation.autowired.collection;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BeanInvoker {

	/**
	 * autowired对于泛型的List
	 * 如果Autowired自动装载BeanInterface泛型的List,
	 * 那么此接口的所有子类实例对象都会自动装载到list中
	 */
	@Autowired
	private List<BeanInterface> list;

	/**
	 * 同样,Autowired自动装载BeanInterface泛型的Map,
	 * 那么此接口的所有子类实例对象都会自动装载到Map中,
	 * 注意:这里map的key将会是Bean的name,默认为首字母小写的Bean类名
	 */
	@Autowired
	private Map<String, BeanInterface> map;

	@Autowired
	@Qualifier("beanImplTwo")
	private BeanInterface beanInterface;

	public void say() {
		if (null != list && 0 != list.size()) {
			System.out.println("list...");
			for (BeanInterface bean : list) {
				System.out.println(bean.getClass().getName());
			}
		} else {
			System.out.println("List<BeanInterface> list is null !!!!!!!!!!");
		}

		System.out.println();

		if (null != map && 0 != map.size()) {
			System.out.println("map...");
			for (Map.Entry<String, BeanInterface> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "      " + entry.getValue().getClass().getName());
			}
		} else {
			System.out.println("Map<String, BeanInterface> map is null !!!!!!!!!!");
		}

		System.out.println();
		if (null != beanInterface) {
			System.out.println(beanInterface.getClass().getName());
		} else {
			System.out.println("beanInterface is null...");
		}
	}

}
