package mms.db.collectiondao;

import mms.db.MessageDao;
import mms.db.UserDao;
import mms.entity.Message;
import mms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
@Primary
public class MessageDaoCollection implements MessageDao {
    private long id = 0;
    private Map<Long, Message> messages = new HashMap<Long, Message>();
    private UserDao userDao;

    @PostConstruct
    public void init() {
        List<User> users = new ArrayList<User>();
        users.addAll(userDao.fetchAll());

        addMessage("Hello from First User", users.get(0));
        addMessage("Double hello from First User", users.get(0));
        addMessage("Greetings from Second User", users.get(1));
        addMessage("Good day from Third User", users.get(2));
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Collection<Message> fetchAll() {
        return messages.values();
    }

    @Override
    public Message fetch(long id) {
        return messages.get(id);
    }

    @Override
    public void saveOrUpdate(Message message) {
        if (message.getId() == null) create(message);
        else update(message);
    }

    @Override
    public void delete(long id) {
        messages.remove(id);
    }

    private void addMessage(String text, User user) {
        Message message = new Message();
        message.setText(text);
        message.setUser(user);

        create(message);
    }

    private void create(Message message) {
        resolveUser(message);

        message.setId(++id);
        messages.put(message.getId(), message);
    }

    private void update(Message message) {
        resolveUser(message);

        Message old = fetch(message.getId());

        if (!old.equals(message)) {
            old.setText(message.getText());
            old.setUser(message.getUser());
        }
    }

    private void resolveUser(Message message) {
        User user = message.getUser();

        User userByName = userDao.fetch(user.getName());
        if (userByName == null) {
            userDao.saveOrUpdate(user);
            message.setUser(userDao.fetch(message.getUser().getId()));
        } else {
            message.setUser(userByName);
        }
    }
}
