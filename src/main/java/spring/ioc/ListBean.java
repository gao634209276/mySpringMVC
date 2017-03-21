package spring.ioc;

import java.util.*;

/**
 */
public class ListBean {
	private List listProperty;
	private Set setProperty;
	private Map mapProperty;
	private Properties property;

	public List getListProperty() {
		return listProperty;
	}

	public void setListProperty(List listProperty) {
		this.listProperty = listProperty;
	}

	public Set getSetProperty() {
		return setProperty;
	}

	public void setSetProperty(Set setProperty) {
		this.setProperty = setProperty;
	}

	public Map getMapProperty() {
		return mapProperty;
	}

	public void setMapProperty(Map mapProperty) {
		this.mapProperty = mapProperty;
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}

	public void printInfo() {
		System.out.println("listProperty:");
		System.out.println(listProperty);
		System.out.println("setProperty:");
		System.out.println(setProperty);
		Set set = mapProperty.entrySet();
		Iterator it = set.iterator();
		System.out.println("mapProperty:");
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			System.out.println("Key " + entry.getKey());
			System.out.println("value " + entry.getValue());
		}
		System.out.println("props: ");
		Set set2 = property.entrySet();
		Iterator it2 = set2.iterator();
		while (it2.hasNext()) {
			Map.Entry entry = (Map.Entry) it2.next();
			System.out.println("key " + entry.getKey());
			System.out.println("value " + entry.getValue());
		}
	}
}
