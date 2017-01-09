package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.dao.UserDTO;
import spring.http.RemoteService;

import java.util.HashMap;
import java.util.Map;

public class Test {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping(value = "/test")
	public ModelAndView test(@RequestParam(value = "name", required = false) String name) {
		UserDTO dto = remoteService.getUser("hello");
		System.out.println(dto);
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("test/hello", model);
	}
}
