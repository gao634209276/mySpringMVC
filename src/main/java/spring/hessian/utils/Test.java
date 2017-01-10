package spring.hessian.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 */
public class Test {
	public static void main(String[] args) {
		testInt();
	}

	public static void testInt(){
		String id = "01";
		System.out.println(Integer.valueOf(id));

	}

	public static void testArray() {
		String test = "-b";
		String[] array = test.split("-");
		System.out.println(Arrays.toString(array));
		if (array[0].equals("")) {
			System.out.println("yes");
		}
	}

	public static void testDate() {
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -10);
		System.out.println(formatter.format(c.getTime()));

	}
}
