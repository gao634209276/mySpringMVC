package spring.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Component注解类声明了bean后,调用时使用注解配置的bean名称
 * 如果没有明确指定bean名称时,默认为该类名称,首字母小写
 * Scope默认是singleton,可以用户设置如:prototype,request,session等
 * prototype表示每次使用改bean时候,都是new的新的对象
 */
//@Component("ioc.bean")
@Scope
@Component
public class BeanAnnotation {
	
	public void say(String arg) {
		System.out.println("BeanAnnotation : " + arg);
	}
	
	public void myHashCode() {
		System.out.println("BeanAnnotation : " + this.hashCode());
	}
	
}
