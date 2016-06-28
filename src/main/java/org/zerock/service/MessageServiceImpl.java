package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.MessageVO;
import org.zerock.persistence.MessageDAO;
import org.zerock.persistence.PointDAO;

import javax.inject.Inject;

/**
 * Created by User on 2016-06-08.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Inject
    private MessageDAO messageDAO;

    @Inject
    private PointDAO pointDAO;

    @Transactional
    @Override
    public void addMessage(MessageVO vo) throws Exception {
        messageDAO.create(vo);
        pointDAO.updatePoint(vo.getSender(), 10);
    }

    @Override
    public MessageVO readMessagae(String uid, Integer mno) throws Exception {
        messageDAO.updateState(mno);
        pointDAO.updatePoint(uid, 5);

        return messageDAO.readMessage(mno);
    }
}
