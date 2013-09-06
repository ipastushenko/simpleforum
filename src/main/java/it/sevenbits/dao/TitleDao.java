package it.sevenbits.dao;

import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Interface title DAO
 * @author Ivan Pastushenko @ sevenbits
 * @version 1.0.0 02.09.2013
 */

public interface TitleDao {
    /**
     * add title
     * @param title title
     * @throws DataAccessException if exception in database
     */
    void create(final Title title) throws DataAccessException;

    /**
     * delete title
     * @param titleEntity title
     * @throws DataAccessException if no titleEntity in database
     */
    void delete(final TitleEntity titleEntity) throws DataAccessException;

    /**
     * get all titles
     * @return list titles
     */
    List<TitleEntity> findAll();

    /**
     * get title by id
     * @param id id of title
     * @return title
     */
    TitleEntity findById(final Long id);
}
