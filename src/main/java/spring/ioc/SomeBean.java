package spring.ioc;

public class SomeBean {
	private OtherBean ob;
	public void printInfo(){
		System.out.println("someBean "+ob);
	}
	public OtherBean getOb() {
		return ob;
	}
	public void setOb(OtherBean ob) {
		this.ob = ob;
	}
}