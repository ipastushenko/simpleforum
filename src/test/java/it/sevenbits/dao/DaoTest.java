package it.sevenbits.dao;

import static org.junit.Assert.*;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.MessageEntity;
import it.sevenbits.entity.hibernate.TitleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Tests DAO hibernate
 * @author Ivan Pastushenko
 * @version 1.0.0 04.09.2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-test.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class DaoTest {
    @Resource(name="messageDao")
    private MessageDao messageDao;
    @Resource(name="titleDao")
    private TitleDao titleDao;

    @Test(expected = DAOException.class)
    @Transactional
    public void deleteMessages() throws DAOException {
        MessageEntity messageEntity = messageDao.findById(new Long(1));
        TitleEntity titleEntity = titleDao.findById(new Long(1));
        titleDao.delete(titleEntity);
        titleDao.delete(titleEntity);
        messageDao.delete(messageEntity);
        messageDao.delete(messageEntity);
        messageDao.delete(messageEntity);
    }

    @Test(expected = DAOException.class)
    @Transactional
    public void addMessages() throws DAOException {
        titleDao.delete(titleDao.findById(new Long(1)));
        messageDao.create(new Message(new Title("Title"), "Message"), new Long(1));
        //messageDao.create(new Message(new Title("Title"), "Message"), new Long(1));
    }

    @Test
    @Transactional
    public void findTitleById() throws DAOException {
        assertNull(titleDao.findById(new Long(100)));
        for (Long i = new Long(1); i <= 12; ++i)
        {
            TitleEntity titleEntity = titleDao.findById(i);
            assertNotNull(titleEntity);
            titleDao.delete(titleEntity);
            List<MessageEntity> messageEntityList = messageDao.findByTitleId(i);
            assertEquals(messageEntityList.size(), 0);
        }
        List<TitleEntity> listTitleEntities = titleDao.findAll();
        assertNull(titleDao.findById(new Long(1)));
        assertEquals(listTitleEntities.size(), 0);
        /*TitleEntity titleEntity = titleDao.findById(new Long(1));
        assertNotNull(titleEntity);
        titleDao.delete(titleEntity);
        //messageDao.create(new Message(new Title("Title"), "Message"), new Long(1));
        titleEntity = titleDao.findById(new Long(2));

        //titleDao.findById(new Long(1)));
        //List<MessageEntity> listMessageEntities = messageDao.findByTitleId(new Long(1));
        //*/
    }
}
