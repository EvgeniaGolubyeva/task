package mms.db;

import mms.entity.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> fetchAll();

    User fetch(long id);
    User fetch(String name);

    void saveOrUpdate(User user);
}
