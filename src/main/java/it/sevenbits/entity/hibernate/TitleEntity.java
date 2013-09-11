package it.sevenbits.entity.hibernate;

import it.sevenbits.entity.Title;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Title entity
 * @author Ivan Pastushenko @ sevenbits
 * @version 30.08.2013
 */
//limit :limit offset :offset
@Entity
@Table(name="title")
public class TitleEntity extends Title implements Serializable{
    private static final long serialVersionUID = 2194724481956723413L;
    private Long id;
    private Long createDate = (new Date()).getTime();
    private Long lastUpdateDate = (new Date()).getTime();

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

    @Column(name = "createDate", nullable = false)
    public Long getCreateDate() {
        return createDate;
    }

    @Column(name = "lastUpdateDate", nullable = false)
    public Long getLastUpdateDate() {
        return lastUpdateDate;
    }

    @PrePersist
    protected void onCreate() {
        createDate = lastUpdateDate = (new Date()).getTime();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = (new Date()).getTime();
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public void setLastUpdateDate(Long lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
