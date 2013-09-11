package it.sevenbits.dao.hibernate;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import it.sevenbits.entity.Title;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Title DAO hibernate
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 09.02.2013
 */
@Repository(value = "titleDao")
@Transactional
public class TitleDaoHibernate implements TitleDao {
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public TitleDaoHibernate(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    @Transactional(readOnly = false)
    public void create(final Title title) throws DAOException {
        try {
            TitleEntity titleEntity = new TitleEntity(title.getName());
            hibernateTemplate.saveOrUpdate(titleEntity);
        }
        catch (DataAccessException e) {
            throw new DAOException();
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete (final TitleEntity titleEntity) throws DAOException {
        try {
            List listMessages = hibernateTemplate.findByNamedQueryAndNamedParam(
                    "findAllMessagesOfTitle", "titleId", titleEntity.getId()
            );
            hibernateTemplate.deleteAll(listMessages);
            hibernateTemplate.delete(titleEntity);
        }
        catch (DataAccessException e) {
            throw new DAOException();
        }
    }

    @Override
    public List<TitleEntity> findByLimitByOrder(final Long limit, final Long offset, final String order) {
        final DetachedCriteria criteria = DetachedCriteria.forClass(TitleEntity.class);
        criteria.addOrder(Order.desc(order));
        return hibernateTemplate.execute(
                new HibernateCallback<List<TitleEntity>>() {
                    @Override
                    public List<TitleEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                        return criteria.getExecutableCriteria(session).add(Restrictions.le(order, offset))
                                .setMaxResults(limit.intValue()).list();
                    }
                }
        );
    }

    @Override
    public List<TitleEntity> findAll() {
        final DetachedCriteria criteria = DetachedCriteria.forClass(TitleEntity.class);
        return hibernateTemplate.execute(
            new HibernateCallback<List<TitleEntity>>() {
                @Override
                public List<TitleEntity> doInHibernate(Session session) throws HibernateException, SQLException {
                    return criteria.getExecutableCriteria(session).add(Restrictions.le("id", new Long(5))).list();
                }
            }
        );
    }

    @Override
    public TitleEntity findById(final Long id) {
        return hibernateTemplate.get(TitleEntity.class, id);
    }
}
