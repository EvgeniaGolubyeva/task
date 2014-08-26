package mms.db.hibernatedao;

import mms.db.MessageDao;
import mms.entity.Message;
import mms.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoHibernate implements MessageDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Message> fetchAll() {
        Session s = sessionFactory.getCurrentSession();

        @SuppressWarnings("unchecked")
        List<Message> res = (List<Message>) s.createQuery("from Message").list();

        return res;
    }

    @Override
    public Message fetch(long id) {
        Session s = sessionFactory.getCurrentSession();

        Message message = (Message) s.get(Message.class, id);

        return message;
    }

    @Override
    public void saveOrUpdate(Message message) {
        Session s = sessionFactory.getCurrentSession();

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) s.createQuery("from User where name = ?")
                .setString(0, message.getUser().getName()).list();
        //if user with requested name exists it is bound to message
        if (users.size() == 1) {
            message.setUser(users.get(0));
        }

        s.saveOrUpdate(message);
    }

    @Override
    public void delete(long id) {
        Session s = sessionFactory.getCurrentSession();

        Message message = (Message) s.load(Message.class, id);
        s.delete(message);
    }
}
