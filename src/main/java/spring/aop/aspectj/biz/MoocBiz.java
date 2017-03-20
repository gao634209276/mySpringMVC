package spring.aop.aspectj.biz;

import org.springframework.stereotype.Service;
import spring.aop.aspectj.MoocMethod;


@Service
public class MoocBiz {

	@MoocMethod("MoocBiz save with MoocMethod.")
	public String save(String arg) {
		System.out.println("MoocBiz save : " + arg);
//		throw new RuntimeException(" Save failed!");
		return " Save success!";
	}

}
