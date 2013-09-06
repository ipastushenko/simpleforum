package it.sevenbits.dao;

import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import org.springframework.dao.DataAccessException;

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
     * @throws DataAccessException if title with titleId no in database
     */
    void create(final Message message, final Long titleId) throws DataAccessException;

    /**
     * delete message
     * @param messageEntity message
     * @throws DataAccessException if no messageEntity in database
     */
    void delete(final MessageEntity messageEntity) throws DataAccessException;

    /**
     * get all message with titleId
     * @param titleId id of title
     * @return list messages
     */
    List<MessageEntity> findByTitleId(final Long titleId);

    /**
     * get message by id
     * @param id id of message
     * @return message
     */
    MessageEntity findById(final Long id);
}
