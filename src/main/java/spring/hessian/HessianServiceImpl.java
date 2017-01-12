package spring.hessian;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import spring.hessian.utils.Parse;
import spring.hessian.utils.SparkServerJDBC;

import java.util.*;

/*
 * 智慧服务接口实现类
 */
@Service
public class HessianServiceImpl implements HessianService {
	private static Log log = LogFactory.getLog(HessianServiceImpl.class);

	private static final String HTABLE = "t_ods_3hall_to_zhfw_hbase";

	//private static String[] a = {"a", "b"};
	public String sayHello(String username) {
		log.info("Hello " + username);
		return "Hello " + username;
	}


	/**
	 * @param settingId
	 * @param label     province:a,b,c
	 *                  netType:2G,3G,4G
	 *                  payType:0,1
	 *                  packageId:
	 *                  age:-50
	 *                  netTime:20160501-
	 *                  netAge:
	 *                  netUseFrequ:1-4,10-
	 *                  mobUseFrequ:4,
	 * @param source
	 * @param fileName
	 * @return
	 */
	@Override
	public HashMap<String, String> queryResult(String settingId, Map<String, String> label, String source, String fileName) {
		HashMap<String, String> result = new HashMap<>();

		String sql = Parse.parseSQL(label);
		log.info(sql);
		String value = SparkServerJDBC.connection(sql, settingId);

		log.info("Execute result: " + value);
		if (source.equals("1")) {
			String path = fileName;
		}

		result.put("0000", "{id:" + settingId + ",result:zhfw_" + settingId + "}");
		log.info("Service result :" + result.get("0000"));
		return result;
	}
}
