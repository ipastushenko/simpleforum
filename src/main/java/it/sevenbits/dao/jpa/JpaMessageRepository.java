package it.sevenbits.dao.jpa;

import it.sevenbits.dao.DAOException;
import it.sevenbits.dao.MessageDao;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Message repository
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0
 * Date: 25.09.13
 */

@Repository(value = "messageDao")
@Transactional
public class JpaMessageRepository implements MessageDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional(readOnly = false)
    public void create(Message message, Long titleId) throws DAOException {
        try {
            TitleEntity titleEntity = entityManager.find(TitleEntity.class, titleId);
            if (titleEntity != null)
            {
                titleEntity.setLastUpdateDate((new Date()).getTime());
                if (entityManager.merge(titleEntity) == null)
                    throw new DAOException();
                MessageEntity messageEntity = new MessageEntity(titleEntity, message.getTextMessage());
                entityManager.persist(messageEntity);
            }
            else
                throw new DAOException();
        }
        catch (Exception e) {
            throw new DAOException();
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(MessageEntity messageEntity) throws DAOException {
        try {
            entityManager.remove(entityManager.merge(messageEntity));
        }
        catch (Exception e) {
            throw new DAOException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageEntity> findByTitleIdByLimitByOrder(Long titleId, Long limit, Long offset, String order) {
        String namedQuery = String.format("select m from MessageEntity m where m.%s <= :offset and m.titleEntity.id = :titleId order by m.%s desc", order, order);
        TypedQuery<MessageEntity> query = entityManager.createQuery(namedQuery, MessageEntity.class);
        query.setParameter("offset", offset);
        query.setParameter("titleId", titleId);
        return query.setMaxResults(limit.intValue()).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageEntity> findByTitleIdByDate(Long titleId, Long date, String order) {
        String namedQuery = String.format("select m from MessageEntity m where m.%s = :date and m.titleEntity.id = :titleId order by m.%s desc", order, order);
        TypedQuery<MessageEntity> query = entityManager.createQuery(namedQuery, MessageEntity.class);
        query.setParameter("date", date);
        query.setParameter("titleId", titleId);

        return query.setMaxResults(date.intValue()).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public MessageEntity findById(Long id) {
        return entityManager.find(MessageEntity.class, id);
    }
}