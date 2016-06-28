package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2016-06-09.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Inject
    private SqlSession session;

    private static final String NAMESPACE = "org.zerock.mapper.UserMapper";

    @Override
    public UserVO login(LoginDTO dto) throws Exception {
        return session.selectOne(NAMESPACE+".login", dto);
    }

    @Override
    public void keepLogin(String uid, String sessionId, Date next) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("sessionId", sessionId);
        paramMap.put("next", next);

        session.update(NAMESPACE+".keepLogin", paramMap);
    }

    @Override
    public UserVO checkUserWithSessionKey(String key) {
        return session.selectOne(NAMESPACE+".checkUserWithSessionKey", key);
    }

}
