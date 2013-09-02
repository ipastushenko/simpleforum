package it.sevenbits.entity.hibernate;

import it.sevenbits.entity.Title;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 8/30/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="title")
@NamedQueries({
        @NamedQuery(
                name="findAllTitle",
                query="select t from TitleEntity t"
        )
})
public class TitleEntity extends Title {
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

    private Long id;
}
