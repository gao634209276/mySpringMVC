package spring.ioc;

public class Student {
	private long id;
	private String name;
	private int age;

	public Student(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Student() {
		System.out.println("in Student()");
	}

}