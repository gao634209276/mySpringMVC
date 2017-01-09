package servlet.controller;

import com.caucho.hessian.client.HessianProxyFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spring.hessian.HessianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {
	private static Log log = LogFactory.getLog(MainController.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = "http://localhost:8080/sinova/hessian/hessianService";
		//String url = "http://10.70.51.11/sinova/hessian/hessianService";
		HessianProxyFactory factory = new HessianProxyFactory();
		HessianService hessianServer = (HessianService) factory.create(HessianService.class, url);

		/*
		String user_mobile = req.getParameter("username");
		String spid = req.getParameter("pwd");
		log.info("页面查询****" + "手机号码：" + user_mobile + "业务编码：" + spid);
		// 调用智慧服务查询接口
		String result = "";
		HashMap<String, String> queryResult = hessianServer.queryResult(user_mobile, spid, "");
		for (Map.Entry<String, String> entry : queryResult.entrySet()) {
			String keyValue = entry.getKey() + "||" + entry.getValue() + ",";
			result += keyValue;
		}
		log.info("页面查询响应结果：" + result);

		if (null != result && result != "") {
			req.setAttribute("result", result);
			req.getRequestDispatcher("result.jsp").forward(req, resp);// 将结果返回给前台页面
			return;
		} else {
			resp.sendRedirect("fail.jsp");
			return;
		}*/

	}

}
