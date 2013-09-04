package it.sevenbits.dao.hibernate;

import it.sevenbits.dao.MessageDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void create(final Message message, final Long titleId) {
        TitleEntity titleEntity = hibernateTemplate.get(TitleEntity.class, titleId);
        if (titleEntity != null) {
            MessageEntity messageEntity = new MessageEntity(titleEntity, message.getTextMessage());
            hibernateTemplate.saveOrUpdate(messageEntity);
        }
    }

    @Transactional(readOnly = false)
    public void delete(final MessageEntity messageEntity) {
        hibernateTemplate.delete(messageEntity);
    }

    public List<MessageEntity> getByTitleId(final Long titleId) {
        return hibernateTemplate.findByNamedQueryAndNamedParam("findAllMessagesOfTitle","titleId", titleId);
    }

    public MessageEntity getMessageById(final Long id) {
        return hibernateTemplate.get(MessageEntity.class, id);
    }
}
