package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServlet
 */
public class HttpInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*String ip = request.getRemoteHost();
		BufferedReader reader = request.getReader();
		String line = "";
		StringBuffer inputString = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			inputString.append(line);
		}*/

		//获取HTTP请求中的参数信息
		String clientName=request.getParameter("request");
		//clientName=new String(clientName.getBytes("ISO-8859-1"),"GB2312");
		//生成HTTP响应结果
		String title="HelloServlet";
		//set content type
		response.setContentType("text/html;charset=GB2312");

		//write html page
		PrintWriter out=response.getWriter();
		out.print("<HTML><HEAD><TITLE>"+title+"</TITLE>");
		out.print("</HEAD><BODY>");
		out.print("doGet");
		out.println("<h1><p>"+clientName+":您好</h1>");
		out.print("</BODY></HTML>");
		out.close();
	}
}
