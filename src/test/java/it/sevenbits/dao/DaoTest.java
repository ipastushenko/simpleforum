package it.sevenbits.dao;

/**
 * Created with IntelliJ IDEA.
 * User: sevenbits
 * Date: 9/4/13
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */

import static org.junit.Assert.*;

import it.sevenbits.entity.hibernate.MessageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context*.xml")
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class DaoTest {
    @Resource(name="messageDao")
    private MessageDao messageDao;

    @Test
    public void testGetEmployeeById() {

        MessageEntity messageEntity = messageDao.getMessageById(new Long(1));
        assertNotNull(messageEntity);
    }
}
