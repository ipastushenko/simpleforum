package it.sevenbits.dao;

import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/2/13
 * Time: 1:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MessageDao {
    void create(final Message message, final Long titleId);
    List<Message> getAll(final Long titleId);
}
