package org.zerock.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016-06-07.
 */
@Repository
public class ReplyDAOImpl implements ReplyDAO {

    @Inject
    private SqlSession session;

    private static final String NAMESPACE = "org.zerock.mapper.ReplyMapper";

    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return session.selectList(NAMESPACE+".list", bno);
    }

    @Override
    public void create(ReplyVO vo) throws Exception {
        session.insert(NAMESPACE+".create", vo);
    }

    @Override
    public void update(ReplyVO vo) throws Exception {
        session.update(NAMESPACE+".update", vo);
    }

    @Override
    public void delete(Integer rno) throws Exception {
        session.delete(NAMESPACE+".delete", rno);
    }

    @Override
    public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("bno", bno);
        paramMap.put("cri", cri);

        return session.selectList(NAMESPACE+".listPage", paramMap);
    }

    @Override
    public int count(Integer bno) throws Exception {
        return session.selectOne(NAMESPACE+".count", bno);
    }

    @Override
    public int getBno(Integer rno) throws Exception {
        return session.selectOne(NAMESPACE+".getBno", rno);
    }

}
