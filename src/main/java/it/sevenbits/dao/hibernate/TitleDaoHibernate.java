package it.sevenbits.dao.hibernate;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import it.sevenbits.entity.Title;
import org.springframework.transaction.annotation.Transactional;

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

    public List<TitleEntity> findAll() {
        return hibernateTemplate.findByNamedQuery("findAllTitle");
    }

    /*public List<TitleEntity> findByLimit(Long limit, Long offset) {
        return hibernateTemplate.findByNamedQueryAndNamedParam("findTitleByLimitAndOffset",
                new String[]{"limit", "offset"}, new Object[] {limit, offset});
    } */

    public TitleEntity findById(final Long id) {
        return hibernateTemplate.get(TitleEntity.class, id);
    }
}
