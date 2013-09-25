package it.sevenbits.dao.hibernate;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.MessageDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Message DAO hibernate
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 09.02.2013
 */
//@Repository(value = "messageDao")
@Transactional
public class MessageDaoHibernate implements MessageDao {
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public MessageDaoHibernate(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    @Transactional(readOnly = false)
    public void create(final Message message, final Long titleId)  throws DAOException {
        try{
            TitleEntity titleEntity = hibernateTemplate.get(TitleEntity.class, titleId);
            if (titleEntity != null)
            {
                titleEntity.setLastUpdateDate((new Date()).getTime());
                hibernateTemplate.saveOrUpdate(titleEntity);
                MessageEntity messageEntity = new MessageEntity(titleEntity, message.getTextMessage());
                hibernateTemplate.saveOrUpdate(messageEntity);
            }
        }
        catch (DataAccessException e) {
            throw new DAOException();
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(final MessageEntity messageEntity) throws DAOException {
        try {
            hibernateTemplate.delete(messageEntity);
        }
        catch (DataAccessException e) {
            throw new DAOException();
        }
    }

    @Override
    public List<MessageEntity> findByTitleIdByLimitByOrder(
            final Long titleId, final Long limit, final Long offset, final String order
    ) {
        final DetachedCriteria criteria = DetachedCriteria.forClass(MessageEntity.class);
        criteria.add(Restrictions.eq("titleEntity.id", titleId));
        criteria.addOrder(Order.desc(order));
        return hibernateTemplate.execute(
            new HibernateCallback<List<MessageEntity>>() {
                @Override
                public List<MessageEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                    return criteria.getExecutableCriteria(session).add(Restrictions.le(order, offset))
                            .setMaxResults(limit.intValue()).list();
                }
            }
        );
    }

    @Override
    public List<MessageEntity> findByTitleIdByDate(final Long titleId, final Long date, final String order) {
        final DetachedCriteria criteria = DetachedCriteria.forClass(MessageEntity.class);
        criteria.add(Restrictions.eq("titleEntity.id", titleId));
        criteria.add(Restrictions.eq(order, date));
        criteria.addOrder(Order.desc(order));
        return hibernateTemplate.execute(
            new HibernateCallback<List<MessageEntity>>() {
                @Override
                public List<MessageEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                    return criteria.getExecutableCriteria(session).list();
                }
            }
        );
    }

    public MessageEntity findById(final Long id) {
        return hibernateTemplate.get(MessageEntity.class, id);
    }
}
