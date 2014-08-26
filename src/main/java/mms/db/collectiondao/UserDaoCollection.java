package mms.db.collectiondao;

import mms.db.UserDao;
import mms.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoCollection implements UserDao {
    private long id = 0;
    private Map<Long, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        addUser("First User");
        addUser("Second User");
        addUser("Third User");
    }

    @Override
    public Collection<User> fetchAll() {
        return users.values();
    }

    @Override
    public User fetch(long id) {
        return users.get(id);
    }

    @Override
    public User fetch(String name) {
        for (User user : users.values()) {
            if (name.equals(user.getName())) return user;
        }
        return null;
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() == null) create(user);
        else update(user);
    }

    private void create(User user) {
        user.setId(++id);
        users.put(user.getId(), user);
    }

    private void update(User user) {
        User old = fetch(user.getId());

        if (!user.equals(old)) {
            old.setName(user.getName());
        }
    }

    private void addUser(String name) {
        User user = new User();
        user.setName(name);
        create(user);
    }
}
