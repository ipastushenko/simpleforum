package it.sevenbits.dao;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/2/13
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */

import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.TitleEntity;

import java.util.List;

public interface TitleDao {
    void create(final Title title);
    List<TitleEntity> getAll();
    TitleEntity getTitleById(final Long id);
    void delete (TitleEntity title);
}
