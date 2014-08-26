package mms.db;

import mms.entity.Message;

import java.util.Collection;

public interface MessageDao {
    Collection<Message> fetchAll();

    Message fetch(long id);

    void delete(long id);
    void saveOrUpdate(Message message);
}
