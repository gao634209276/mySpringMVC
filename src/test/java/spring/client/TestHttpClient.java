package spring.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpClient {

	public static void main(String[] args) {

		System.out.println(sendGet("method=sayHello"));
	}

	private static String url = "http://localhost:8080/http/httpInterface";

	/**
	 * @param param method=sayHello
	 */
	public static String sendPost(String param) {
		PrintWriter out;
		BufferedReader in;
		String result = "";
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			out.close();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 采用http get方式发送请求
	 *
	 * @param param request=test
	 */
	public static String sendGet(String param) {
		String result = "";
		BufferedReader in = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url + "?" + param).openConnection();
			connection.setRequestMethod("GET");
			connection.setUseCaches(true);
			connection.connect();
			// Map<String, List<String>> map = connection.getHeaderFields();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
