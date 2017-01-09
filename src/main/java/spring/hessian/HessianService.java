package spring.hessian;

import java.util.HashMap;

public interface HessianService {
	public String sayHello(String username);

	/**
	 * 智慧服务订购查询接口
	 *
	 * @param user_mobile 号码
	 * @param codes       编码（业务/接口）	 split by common e.g （10010,10020）
	 * @param datetime    时间（月截止时间-年月日时）
	 * @return HashMap<code:json>
	 */
	public HashMap<String, String> queryResult(String user_mobile, String codes, String datetime);
}
