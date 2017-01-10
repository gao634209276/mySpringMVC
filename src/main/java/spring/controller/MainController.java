package spring.controller;

import com.caucho.hessian.client.HessianProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.hessian.HessianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * 采用注解的方式，可以明确地定义该类为处理请求的Controller类
 */
@Controller
public class MainController {

    @Autowired
    @Qualifier(value = "hessianServiceImpl")
    private HessianService hessianServer;

    // 定义一个请求映射,value为请求的url为/说明，该请求首页请求，method用以指定该请求类型，一般为get和post；
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        // 处理完该请求后返回的页面，此请求返回 index.jsp页面
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        // 处理完该请求后返回的页面，此请求返回 index.jsp页面
        return "test";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String hessian(HttpServletRequest request) throws IOException, ServletException {
        // 获取hession的get请求，创建远程代理HessianService，通过hessian RPC进行调用远程服务
        //String url = "http://localhost:8080/hessian/hessianService";
        //String url = "http://10.70.51.11/sinova/hessian/hessianService";
        //HessianProxyFactory factory = new HessianProxyFactory();
        //HessianService hessianServer = (HessianService) factory.create(HessianService.class, url);
        String args = request.getParameter("args");
        String result = hessianServer.sayHello(args);
        System.out.println(result);
        // request.getRequestDispatcher("result.jsp").forward(request, resp);
        // 处理完该请求后返回的页面，此请求返回 index.jsp页面
        return "result";
    }


}
