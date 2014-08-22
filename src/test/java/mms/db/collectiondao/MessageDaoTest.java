package mms.db.collectiondao;

import mms.entity.Message;
import mms.entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageDaoTest {
    private MessageDaoCollection messageDao;

    @Before
    public void setUp() {
        UserDaoCollection userDao = new UserDaoCollection();
        userDao.init();

        messageDao = new MessageDaoCollection();
        messageDao.setUserDao(userDao);
        messageDao.init();
    }

    @Test
    public void fetchAllAfterInitShouldReturn4() {
        assertTrue(messageDao.fetchAll().size() == 4);
    }

    @Test
    public void fetchWithExisingIdShouldReturnUser() {
        assertNotNull(messageDao.fetch(1));
    }

    @Test
    public void fetchWithNonExisingIdShouldReturnNull() {
        assertNull(messageDao.fetch(0));
    }

    @Test
    public void messageShouldBeCreated() {
        User user = createMessageWithUser("First User");
        Message createdMessage = messageDao.fetch(5);

        assertTrue(createdMessage.getText().equals("Hi"));
        assertTrue(createdMessage.getUser().getName().equals(user.getName()));
        assertTrue(messageDao.fetchAll().size() == 5);
    }

    @Test
    public void messageShouldBeCreatedWithExistingUser() {
        User user = createMessageWithUser("First User");
        Message createdMessage = messageDao.fetch(5);

        assertFalse(createdMessage.getUser() == user);
        assertTrue(createdMessage.getUser().getName().equals(user.getName()));
    }

    @Test
    public void messageShouldBeCreatedWithNewUser() {
        User user = createMessageWithUser("New User");
        Message createdMessage = messageDao.fetch(5);

        assertTrue(createdMessage.getUser() == user);
    }

    @Test
    public void messageShouldBeUpdated() {
        Message message = messageDao.fetch(1);
        message.setText("111");

        User user = new User();
        user.setId(2L);
        user.setName("Second User");
        message.setUser(user);

        messageDao.saveOrUpdate(message);

        Message messageAfterUpdate = messageDao.fetch(1);

        assertEquals(message, messageAfterUpdate);
        assertEquals(message.getText(), messageAfterUpdate.getText());
        assertEquals(message.getUser(), messageAfterUpdate.getUser());
    }

    @Test
    public void messageShouldUpdateValuesNotReferences() {
        Message message = new Message();
        message.setId(1L);
        message.setText("111");

        User user = new User();
        user.setId(1L);
        user.setName("First User");
        message.setUser(user);

        messageDao.saveOrUpdate(message);

        Message messageAfterUpdate = messageDao.fetch(1);

        assertFalse(message == messageAfterUpdate);
        assertEquals(message.getText(), messageAfterUpdate.getText());
        assertEquals(message.getUser(), messageDao.fetch(2).getUser());
    }

    @Test
    public void userShouldBeUpdatedInAllMessages() {
        Message message1 = messageDao.fetch(1);
        message1.getUser().setName("1");

        assertTrue(messageDao.fetch(2).getUser() == messageDao.fetch(1).getUser());
    }

    @Test
    public void messageShouldBeDeleted() {
        messageDao.delete(1);

        assertTrue(messageDao.fetchAll().size() == 3);
        assertNull(messageDao.fetch(1));
    }

    private User createMessageWithUser(String userName) {
        Message message = new Message();

        User user = new User();
        user.setName(userName);

        message.setUser(user);
        message.setText("Hi");

        messageDao.saveOrUpdate(message);

        return user;
    }
}
