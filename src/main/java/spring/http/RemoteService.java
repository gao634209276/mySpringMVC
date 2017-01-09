package spring.http;

import org.springframework.stereotype.Service;
import spring.dao.UserDTO;

/**
 * Created by 小小科学家 on 2017/1/9.
 */
@Service
public interface RemoteService {
	UserDTO getUser(String hello);
}
