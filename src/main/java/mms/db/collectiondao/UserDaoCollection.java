package mms.db.collectiondao;

import mms.db.UserDao;
import mms.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import java.util.*;

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
        Optional<User> res = users.values().stream().filter((u) -> u.getName().equals(name)).findFirst();
        return res.isPresent() ? res.get() : null;
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
