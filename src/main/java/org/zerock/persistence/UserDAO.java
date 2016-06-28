package org.zerock.persistence;

import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

import java.util.Date;

/**
 * Created by User on 2016-06-09.
 */
public interface UserDAO {

    public UserVO login(LoginDTO dto) throws Exception;

    public void keepLogin(String uid, String sessionId, Date next);

    public UserVO checkUserWithSessionKey(String key);

}
