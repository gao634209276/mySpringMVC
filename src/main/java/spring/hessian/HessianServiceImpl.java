package spring.hessian;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/*
 * 智慧服务接口实现类
 */
@Service("hessianService")
public class HessianServiceImpl implements HessianService {
	private static Log log = LogFactory.getLog(HessianServiceImpl.class);

	private static final String HTABLE = "t_ods_3hall_to_zhfw_hbase";

	public String sayHello(String username) {
		return "Hello " + username;
	}

	public HashMap<String, String> queryResult(String user_mobile, String codes, String datetime) {
		log.info("智慧服务查询接口,hbase table：" + HTABLE + "   手机号码：" + user_mobile + ",   业务编码：" + codes);

		log.info("zhfw result:" + "");
		return null;
	}
}
