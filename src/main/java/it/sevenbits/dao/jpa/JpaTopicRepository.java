package it.sevenbits.dao.jpa;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.TitleDao;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Iterator;
import java.util.List;

/**
 * Topic repository
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0
 * Date: 25.09.13
 */

@Repository(value = "titileDao")
@Transactional
public class JpaTopicRepository implements TitleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = false)
    public void create(Title title) throws DAOException {
        try {
            TitleEntity titleEntity = new TitleEntity(title.getName());
            entityManager.persist(titleEntity);
        }
        catch (Exception e) {
            throw new DAOException();
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(TitleEntity titleEntity) throws DAOException {
        try {
            String namedQuery = "select m from MessageEntity m where m.titleEntity.id = :titleId";
            TypedQuery<MessageEntity> query = entityManager.createQuery(namedQuery, MessageEntity.class);
            query.setParameter("titleId", titleEntity.getId());
            List<MessageEntity> messageEntityes = query.getResultList();
            Iterator<MessageEntity> iterator = messageEntityes.iterator();
            while(iterator.hasNext()) {
                entityManager.remove(entityManager.merge(iterator.next()));
            }
            entityManager.remove(entityManager.merge(titleEntity));
        }
        catch (Exception e) {
            throw new DAOException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TitleEntity> findByLimitByOrder(Long limit, Long offset, String order) {
        String namedQuery = String.format("select t from TitleEntity t where t.%s <= :offset order by t.%s desc", order, order);
        TypedQuery<TitleEntity> query = entityManager.createQuery(namedQuery, TitleEntity.class);
        query.setParameter("offset", offset);

        return query.setMaxResults(limit.intValue()).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TitleEntity> findByDateByOrder(Long date, String order) {
        String namedQuery = String.format("select t from TitleEntity t where t.%s = :date order by t.%s desc", order, order);
        TypedQuery<TitleEntity> query = entityManager.createQuery(namedQuery, TitleEntity.class);
        query.setParameter("date", date);

        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public TitleEntity findById(Long id) {
        return entityManager.find(TitleEntity.class, id);
    }
}
