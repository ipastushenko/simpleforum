package it.sevenbits.entity.hibernate;

import it.sevenbits.entity.Message;
import it.sevenbits.entity.Title;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 8/30/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitleEntity(TitleEntity titleEntity) {
        this.titleEntity = titleEntity;
        super.setTitle(titleEntity);
    }

    private Long id;
    private TitleEntity titleEntity;
    private static final long serialVersionUID = 4385234262720442213L;
}
