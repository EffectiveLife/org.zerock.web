package org.zerock.persistence;

import org.zerock.domain.MessageVO;

/**
 * Created by User on 2016-06-08.
 */
public interface MessageDAO {

    public void create(MessageVO vo) throws Exception;
    public MessageVO readMessage(Integer mid) throws Exception;
    public void updateState(Integer mid) throws Exception;

}
