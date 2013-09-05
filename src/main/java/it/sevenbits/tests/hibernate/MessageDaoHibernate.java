package it.sevenbits.tests.hibernate;

import it.sevenbits.tests.MessageDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Message DAO hibernate
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 09.02.2013
 */
@Repository(value = "messageDao")
@Transactional
public class MessageDaoHibernate implements MessageDao {
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public MessageDaoHibernate(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Transactional(readOnly = false)
    public void create(final Message message, final Long titleId) throws DataAccessException, NullPointerException {
        if (message == null || titleId == null) {
            throw new NullPointerException();
        }

        TitleEntity titleEntity = hibernateTemplate.get(TitleEntity.class, titleId);
        MessageEntity messageEntity = new MessageEntity(titleEntity, message.getTextMessage());
        hibernateTemplate.saveOrUpdate(messageEntity);
    }

    @Transactional(readOnly = false)
    public void delete(final MessageEntity messageEntity) throws DataAccessException, NullPointerException {
        if (messageEntity == null) {
            throw new NullPointerException();
        }

        hibernateTemplate.delete(messageEntity);
    }

    public List<MessageEntity> findByTitleId(final Long titleId) throws NullPointerException {
        if (titleId == null) {
            throw new NullPointerException();
        }

        return hibernateTemplate.findByNamedQueryAndNamedParam("findAllMessagesOfTitle","titleId", titleId);
    }

    public MessageEntity findById(final Long id) throws NullPointerException {
        if (id == null) {
            throw new NullPointerException();
        }

        return hibernateTemplate.get(MessageEntity.class, id);
    }
}
