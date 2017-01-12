package spring.hessian.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Test {

	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	private static Log log = LogFactory.getLog(Test.class);

	private static final String DRIVER = "org.apache.hive.jdbc.HiveDriver";
	//private static final String CONN = "jdbc:hive2://10.70.11.13:10001/default?hive.server2.transport.mode=http;hive.server2.thrift.http.path=cliservice";
	private static final String CONN = "jdbc:hive2://10.70.11.13:10001/default;transportMode=http;httpPath=cliservice";
	private static final String USER = "sinova";

	public static void main(String[] args) {
		testString();
	}

	public static void testString() {
		String test = "ab-";
		System.out.println(test.indexOf("-"));
		System.out.println(test.length());
		System.out.println(test.substring(test.indexOf("-") + 1, test.length()));

	}

	public static void testJDBC() {
		Connection conn = null;
		ResultSet resultSet = null;
		String result = "";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONN, USER, "");
			log.info("Connection to SparkThriftServer..." + CONN);

			//PreparedStatement preparedStatement = conn.prepareStatement(sql);
			//preparedStatement.setInt(1, 30);
			//resultSet = preparedStatement.executeQuery();
			//preparedStatement.execute();
			Statement stat = conn.createStatement();
			log.info("use default: " + stat.execute("use default"));
			//log.info("drop table if exists tmp_zhfw_result: " + stat.execute("drop table if exists tmp_zhfw_result"));
			String create = "create table if not exists tmp_zhfw_result" +
					" (mobile string)" +
					" row format delimited " +
					" fields terminated by '\t'";
			resultSet = stat.executeQuery(create);
			// stat.execute("create table if not exists  tmp_zhfw_result(mobile string)row format delimited lines terminated by '\t'");

			log.info("Execute truncate table tmp_zhfw_result: " + stat.execute("truncate table tmp_zhfw_result"));
			stat.execute("truncate table tmp_zhfw_result");
			log.info("ExecuteQuery...");
			while (resultSet.next()) {
				result = resultSet.getString(0);// 此处的数据应该保存到Parquet中等
				log.info("create Result: " + resultSet.getString(0));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				assert resultSet != null;
				resultSet.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static void testLog() {
		log.info("test");
		logger.info("test");

	}


	public static void testInt() {
		String id = "01";
		System.out.println(Integer.valueOf(id));

	}

	public static void testArray() {
		String test = "-b";
		if (test.startsWith("-") || test.endsWith("-")) {

		}
		String[] array = test.split("-");
		System.out.println(Arrays.toString(array));


		if (array[0].equals("")) {
			System.out.println("yes");
		}

		System.out.println(test.indexOf("x"));
	}

	public static void testDate() {
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -10);
		System.out.println(formatter.format(c.getTime()));
	}
}
