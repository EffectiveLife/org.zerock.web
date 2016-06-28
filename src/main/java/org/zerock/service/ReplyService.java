package org.zerock.service;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import java.util.List;

/**
 * Created by User on 2016-06-07.
 */
public interface ReplyService {

    public void addReply(ReplyVO vo) throws Exception;

    public List<ReplyVO> listReply(Integer bno) throws Exception;

    public void modifyReply(ReplyVO vo) throws Exception;

    public void removeReply(Integer rno) throws Exception;

    public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;

    public int count(Integer bno) throws Exception;

}
