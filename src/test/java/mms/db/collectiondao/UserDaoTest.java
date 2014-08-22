package mms.db.collectiondao;

import mms.entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDaoCollection userDao;

    @Before
    public void setUp() {
        userDao = new UserDaoCollection();
        userDao.init();
    }

    @Test
    public void fetchAllAfterInitShouldReturn3() {
        assertTrue(userDao.fetchAll().size() == 3);
    }

    @Test
    public void fetchWithExisingIdShouldReturnUser() {
        assertNotNull(userDao.fetch(1));
    }

    @Test
    public void fetchWithNonExisingIdShouldReturnNull() {
        assertNull(userDao.fetch(0));
    }


    @Test
    public void fetchByExistingNameShouldReturnUser() {
        User user = userDao.fetch("First User");
        assertNotNull(user);
        assertTrue(user.getId() == 1);
    }

    @Test
    public void fetchByNonExistingNameShouldReturnNull() {
        User user = userDao.fetch("SomeName");
        assertNull(user);
    }

    @Test
    public void userShouldBeCreated() {
        User user = new User();
        user.setName("222");
        userDao.saveOrUpdate(user);

        assertNotNull(userDao.fetch("222"));
        assertTrue(userDao.fetchAll().size() == 4);
    }

    @Test
    public void userShouldUpdateValuesNotReferences() {
        User oldUser = userDao.fetch(1);

        User newUser = new User();
        newUser.setId(1L);
        newUser.setName("222");
        userDao.saveOrUpdate(newUser);

        User userAfterUpdate = userDao.fetch(1);

        assertTrue(userAfterUpdate.getId() == 1L);
        assertEquals(userAfterUpdate.getName(), "222");

        assertTrue(oldUser == userAfterUpdate);
    }
}
