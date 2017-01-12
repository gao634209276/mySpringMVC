package spring.hessian;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("hessianService")
public interface HessianService {
    public String sayHello(String username);

    /**
     * @param settingId 提交id,长度小于20
     * @param label     标签数据,key为标签,value为条件,没有条件的不加标签
     * @param source    1表示独立完成,不需要其他渠道协助;2表示需要其他渠道数据辅助完成
     * @param fileName  其他渠道的ftp文件path
     * @return 成功返回0000, 描述信息待定
     */
    public HashMap<String, String> queryResult(String settingId, Map<String, String> label, String source, String fileName);
}
