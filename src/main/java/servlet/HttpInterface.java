package servlet;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

		//获取HTTP请求中的参数信息
		String method = request.getParameter("method");
		//生成HTTP响应结果
		//set content type
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> map = new HashMap<String,String>();
		mapper.writeValueAsString(map);
		if(method.equals("sayHello")){
			out.print("hello");
		}else{
			out.print("wrong request");
		}
		//write html page
		/*out.print("<HTML>");
		out.print("<HEAD><TITLE>test</TITLE></HEAD>");
		out.print("<BODY>");
		out.print("doGet");
		out.println("<h1><p>hello</h1>");
		out.print("</BODY>");
		out.print("</HTML>");*/
		out.flush();
		out.close();
	}
}
