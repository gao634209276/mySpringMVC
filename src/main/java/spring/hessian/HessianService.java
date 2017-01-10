package spring.hessian;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("hessianService")
public interface HessianService {
	public String sayHello(String username);

	/**
	 * settingId:String,len=20
	 * label:String
	 * source:String,1:独立完成,不需要其他渠道协助;2:需要其他渠道数据辅助完成
	 * filename:String,其他渠道的ftp文件path
	 */
	public HashMap<String, String> queryResult(String settingId, HashMap<String, String> label, String source, String fileName);
}
