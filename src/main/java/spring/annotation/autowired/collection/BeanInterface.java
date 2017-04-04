package spring.annotation.autowired.collection;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public interface BeanInterface {
}

/**
 * 使用Order注解
 * 可以在Autowired装载该Bean接口泛型的List中对所有子类排序
 * 而对Autowired装载的Map不会排序
 */
@Order(1)
@Component
class BeanImplTwo implements BeanInterface {
}

@Order(2)
@Component
class BeanImplOne implements BeanInterface {
}