package spring.hessian;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import spring.hessian.utils.Parse;

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
	public HashMap<String, String> queryResult(String settingId, HashMap<String, String> label, String source, String fileName) {


		String sql = Parse.parseSQL(label);
		log.info(sql);
		//todo jdbc

		if (source.equals("1")) {
			String path = fileName;
			//todo jdbc
		}
		return null;
	}
}
