package it.sevenbits.dao;

import static org.junit.Assert.*;
import it.sevenbits.entity.Message;
import it.sevenbits.entity.Title;
import it.sevenbits.entity.hibernate.MessageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

/**
 * Tests DAO hibernate
 * @author Ivan Pastushenko
 * @version 1.0.0 04.09.2013
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-test.xml"})
public class DaoTest {
    @Resource(name="messageDao")
    private MessageDao messageDao;
    @Resource(name="titleDao")
    private TitleDao titleDao;

    @Test
    public void deleteMessages() {
        MessageEntity messageEntity = messageDao.findById(new Long(1));
        messageDao.delete(messageEntity);
        try {
            messageDao.delete(messageEntity);
            assertTrue(false);
        }
        catch (DataAccessException e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void addMessages() {
        try {
            titleDao.delete(titleDao.findById(new Long(1)));
            messageDao.create(new Message(new Title("Title"), "Message"), new Long(1));
            assertTrue(false);
        }
        catch (DataAccessException e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void findTitleById() {
        try {
            assertNull(titleDao.findById(new Long(100)));
            assertTrue(true);
        }
        catch (DataAccessException e)
        {
            assertTrue(false);
        }
    }
}
