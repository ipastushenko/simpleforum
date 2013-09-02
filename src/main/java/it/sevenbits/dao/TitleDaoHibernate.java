package it.sevenbits.dao;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/2/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */

import it.sevenbits.entity.hibernate.TitleEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import it.sevenbits.entity.Title;
import java.util.List;

@Repository(value = "titleDao")
public class TitleDaoHibernate implements TitleDao {
    @Autowired
    public TitleDaoHibernate(final SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void create(final Title title) {
        TitleEntity titleEntity = new TitleEntity(title.getName());
        hibernateTemplate.save(titleEntity);
    }

    public List<TitleEntity> getAll() {
        return hibernateTemplate.findByNamedQuery("findAllTitle");
    }

    public TitleEntity getTitleById(final Long id) {
        return hibernateTemplate.get(TitleEntity.class, id);
    }

    private HibernateTemplate hibernateTemplate;
}
