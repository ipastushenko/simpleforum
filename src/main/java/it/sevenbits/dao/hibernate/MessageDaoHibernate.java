package it.sevenbits.dao.hibernate;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.MessageDao;
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
    public void create(final Message message, final Long titleId)  throws DAOException {
        try{
            TitleEntity titleEntity = hibernateTemplate.get(TitleEntity.class, titleId);
            MessageEntity messageEntity = new MessageEntity(titleEntity, message.getTextMessage());
            hibernateTemplate.saveOrUpdate(messageEntity);
        }
        catch (DataAccessException e) {
            throw new DAOException();
        }
    }

    @Transactional(readOnly = false)
    public void delete(final MessageEntity messageEntity) throws DAOException {
        try {
            hibernateTemplate.delete(messageEntity);
        }
        catch (DataAccessException e) {
            throw new DAOException();
        }
    }

    public List<MessageEntity> findByTitleId(final Long titleId) throws DataAccessException {
        return hibernateTemplate.findByNamedQueryAndNamedParam("findAllMessagesOfTitle", "titleId", titleId);
    }

    /*@Override
    public List<TitleEntity> findByTitleIdByLimit(Long titleId, Long limit, Long offset) {
        return hibernateTemplate.findByNamedQueryAndNamedParam("findMessagesByTitleByLimitAndOffset",
                new String[] {"titleId", "limit", "offset"}, new Object[] {titleId, limit, offset});
    } */

    public MessageEntity findById(final Long id) {
        return hibernateTemplate.get(MessageEntity.class, id);
    }
}
