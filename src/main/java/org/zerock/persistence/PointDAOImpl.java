package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2016-06-08.
 */
@Repository
public class PointDAOImpl implements PointDAO {

    @Inject
    private SqlSession session;

    private static final String NAMESPACE = "org.zerock.mapper.PointMapper";
    @Override
    public void updatePoint(String uid, int point) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("uid", uid);
        paramMap.put("point", point);

        session.update(NAMESPACE+".updatePoint",  paramMap);
    }
}
