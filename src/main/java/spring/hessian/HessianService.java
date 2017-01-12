package spring.hessian;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("hessianService")
public interface HessianService {
	public String sayHello(String username);

	/**
	 * @param settingId len=20
	 * @param label     标签数据
	 * @param source    1:独立完成,不需要其他渠道协助;2:需要其他渠道数据辅助完成
	 * @param fileName  其他渠道的ftp文件path
	 * @return
	 */
	public HashMap<String, String> queryResult(String settingId, Map<String, String> label, String source, String fileName);
}
