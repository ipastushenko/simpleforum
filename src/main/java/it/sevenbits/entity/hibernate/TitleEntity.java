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
public class TitleEntity extends Title {
    public TitleEntity() {
        super();
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
