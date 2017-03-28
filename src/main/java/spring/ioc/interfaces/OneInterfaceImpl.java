package spring.ioc.interfaces;

/**
 *
 */
public class OneInterfaceImpl implements OneInterface {

	public void say(String arg) {
		System.out.println("ServiceImpl say: " + arg);
	}

	public static void main(String[] args) {
		OneInterface instance = new OneInterfaceImpl();
		instance.say("hello");
	}

}
