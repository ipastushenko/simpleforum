package it.sevenbits.dao.hibernate;

import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void create(final Title title) {
        TitleEntity titleEntity = new TitleEntity(title.getName());
        hibernateTemplate.saveOrUpdate(titleEntity);
    }

    @Transactional(readOnly = false)
    public void delete (final TitleEntity titleEntity) {
        List<MessageEntity> listMessages = hibernateTemplate.findByNamedQueryAndNamedParam(
                "findAllMessagesOfTitle","titleId", titleEntity.getId()
        );
        hibernateTemplate.deleteAll(listMessages);
        hibernateTemplate.delete(titleEntity);
    }

    public List<TitleEntity> getAll() {
        return hibernateTemplate.findByNamedQuery("findAllTitle");
    }

    public TitleEntity getTitleById(final Long id) {
        return hibernateTemplate.get(TitleEntity.class, id);
    }
}