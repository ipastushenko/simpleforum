package it.sevenbits.dao;

import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import java.util.List;

/**
 * Interface message DAO
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 02.09.2013
 */
public interface MessageDao {
    /**
     * add message with titleId
     * @param message message
     * @param titleId id of title
     */
    void create(final Message message, final Long titleId);

    /**
     * delete message
     * @param message message
     */
    void delete(final MessageEntity message);

    /**
     * get all message with titleId
     * @param titleId id of title
     * @return list messages
     */
    List<MessageEntity> getByTitleId(final Long titleId);

    /**
     * get message by id
     * @param id id of message
     * @return message
     */
    MessageEntity getMessageById(final Long id);
}
