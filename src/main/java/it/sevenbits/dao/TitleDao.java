package it.sevenbits.dao;

import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.TitleEntity;
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
     */
    void create(final Title title);

    /**
     * delete title
     * @param titleEntity title
     */
    void delete(final TitleEntity titleEntity);

    /**
     * get all titles
     * @return list titles
     */
    List<TitleEntity> getAll();

    /**
     * get title by id
     * @param id id of title
     * @return title
     */
    TitleEntity getTitleById(final Long id);
}
