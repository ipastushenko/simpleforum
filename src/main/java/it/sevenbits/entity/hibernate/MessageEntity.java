package it.sevenbits.entity.hibernate;

import it.sevenbits.entity.Message;
import it.sevenbits.entity.Title;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Message entity
 * @author Ivan Pastushenko @ sevenbits
 * @version 30.08.2013
 * @version 30.08.2013
 */
@Entity
@NamedQueries({
        @NamedQuery(
            name="findAllMessagesOfTitle",
            query="select m from MessageEntity m where m.titleEntity.id = :titleId"
        )
})
@Table(name="message")
public class MessageEntity extends Message implements Serializable {
    private static final long serialVersionUID = 4385234262720442213L;
    private Long id;
    private TitleEntity titleEntity;
    private Long createDate = (new Date()).getTime();

    public MessageEntity() {
        super();
    }

    public MessageEntity(final TitleEntity titleEntity, final String textMessage) {
        super(titleEntity, textMessage);
        this.titleEntity = titleEntity;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
    @JoinColumn(name="titleId", nullable = false)
    public TitleEntity getTitleEntity() {
        return titleEntity;
    }

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "body", length = 256, nullable = false)
    @Override
    public String getTextMessage() {
        return super.getTextMessage();
    }

    @Column(name = "createDate", nullable = false)
    public Long getCreateDate() {
        return createDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitleEntity(TitleEntity titleEntity) {
        this.titleEntity = titleEntity;
        super.setTitle(titleEntity);
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
