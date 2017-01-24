package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.service.ServiceIf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 采用注解的方式，可以明确地定义该类为处理请求的Controller类
 */
@Controller
public class MainController {

    @Autowired
    @Qualifier(value = "serviceImpl")
    private ServiceIf service;

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
        String args = request.getParameter("args");
        String result = service.sayHello();
        System.out.println(result);
        return "result";
    }
}
