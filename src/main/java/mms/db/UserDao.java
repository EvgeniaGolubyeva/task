package mms.db;

import mms.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserDao {
    Collection<User> fetchAll();

    User fetch(long id);
    User fetch(String name);

    void saveOrUpdate(User user);
}
