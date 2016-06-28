package org.zerock.service;

import org.zerock.domain.MessageVO;

/**
 * Created by User on 2016-06-08.
 */
public interface MessageService {

    public void addMessage(MessageVO vo) throws Exception;
    public MessageVO readMessagae(String uid, Integer mno) throws Exception;

}
