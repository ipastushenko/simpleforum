package it.sevenbits.dao;

import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/2/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository(value = "messageDao")
@Transactional
public class MessageDaoHibernate implements MessageDao {
    @Autowired
    public MessageDaoHibernate(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    @Transactional(readOnly = false)
    public void create(final Message message, final Long titleId) {
        TitleEntity titleEntity = hibernateTemplate.get(TitleEntity.class, titleId);
        MessageEntity messageEntity = new MessageEntity(titleEntity, message.getTextMessage());
        hibernateTemplate.saveOrUpdate(messageEntity);
    }
    public List<Message> getAll(final Long titleId) {
        return hibernateTemplate.findByNamedQueryAndNamedParam("findAllMessagesOfTitle","titleId", titleId);
    }
    @Transactional(readOnly = false)
    public void delete(final Message message, final Long titleId) {
        TitleEntity titleEntity = hibernateTemplate.get(TitleEntity.class, titleId);
        MessageEntity messageEntity = new MessageEntity(titleEntity, message.getTextMessage());
        hibernateTemplate.delete(messageEntity);
    }

    private HibernateTemplate hibernateTemplate;
}
