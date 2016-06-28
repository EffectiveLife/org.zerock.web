package org.zerock.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;
import org.zerock.persistence.ReplyDAO;

import javax.inject.Inject;

/**
 * Created by User on 2016-06-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/spring/**/*.xml"})
public class ReplyDAOTest {

    @Inject
    private ReplyDAO dao;

    private static Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);

    @Test
    public void testReplyCreate() throws Exception{

        ReplyVO vo = new ReplyVO();
        vo.setBno(182);
        vo.setReplytext("삭제할 댓글 등록");
        vo.setReplyer("user00");

        dao.create(vo);

    }

}
