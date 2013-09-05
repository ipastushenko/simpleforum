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
     * @throws NullPointerException if message is null or titleId is null
     * @throws DataAccessException if title with titleId no in database
     */
    void create(final Message message, final Long titleId) throws DataAccessException, NullPointerException;

    /**
     * delete message
     * @param messageEntity message
     * @throws NullPointerException if message is null
     * @throws DataAccessException if no messageEntity in database
     */
    void delete(final MessageEntity messageEntity) throws DataAccessException, NullPointerException;

    /**
     * get all message with titleId
     * @param titleId id of title
     * @return list messages
     * @throws NullPointerException if titleId is null
     */
    List<MessageEntity> findByTitleId(final Long titleId) throws NullPointerException;

    /**
     * get message by id
     * @param id id of message
     * @return message
     * @throws NullPointerException if id is null
     */
    MessageEntity findById(final Long id) throws NullPointerException;
}
