package spring.ioc.bean.autowiring;

/**
 * 修改resource/ioc.bean/spring-autoWiring.xml中的
 * <beans default-autowriring="xx"></beans>
 * byName,byType,constructor
 * 在test/spring/ioc.bean/autowiring/TestAutoWiring.java中测试
 */
public class AutoWiringService {

	private AutoWiringDAO autoWiringDAO;

	public AutoWiringService(AutoWiringDAO autoWiringDAO) {
		System.out.println("AutoWiringService");
		this.autoWiringDAO = autoWiringDAO;
	}

	public void setAutoWiringDAO(AutoWiringDAO autoWiringDAO) {
		System.out.println("setAutoWiringDAO");
		this.autoWiringDAO = autoWiringDAO;
	}

	public void say(String word) {
		this.autoWiringDAO.say(word);
	}

}
