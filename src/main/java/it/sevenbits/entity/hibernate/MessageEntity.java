package it.sevenbits.entity.hibernate;

import it.sevenbits.entity.Message;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 8/30/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="message")
public class MessageEntity extends Message {
    public MessageEntity() {
        super();
    }

    @ManyToOne
    @JoinColumn(name="title_id")
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
    }

    private Long id;
    private TitleEntity titleEntity;
}
