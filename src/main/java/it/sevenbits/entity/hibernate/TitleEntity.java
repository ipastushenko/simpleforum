package it.sevenbits.entity.hibernate;

import it.sevenbits.entity.Title;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Title entity
 * @author Ivan Pastushenko @ sevenbits
 * @version 30.08.2013
 */
//limit :limit offset :offset
@Entity
@Table(name="title")
@NamedQueries({
        @NamedQuery(
                name="findAllTitle",
                query="select t from TitleEntity t"
        )/*,
        @NamedQuery(
                name="findTitleByLimitAndOffset",
                query="select t from TitleEntity t limit :limit offset :offset"
        )  */
})
public class TitleEntity extends Title implements Serializable{
    private static final long serialVersionUID = 2194724481956723413L;
    private Long id;

    public TitleEntity() {
        super();
    }

    public TitleEntity(String name) {
        super(name);
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "name", length = 256, nullable = false)
    @Override
    public String getName() {
        return super.getName();
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
