package spring.ioc.injection.dao;

/**
 * Dao的实现,模拟数据库保存操作
 * 一般为通用操作
 */
public class InjectionDAOImpl implements InjectionDAO {

	public void save(String arg) {
		//模拟数据库保存操作
		System.out.println("保存数据：" + arg);
	}

}
