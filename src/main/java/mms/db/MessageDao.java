package mms.db;

import mms.entity.Message;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface MessageDao {
    Collection<Message> fetchAll();

    Message fetch(long id);

    void delete(long id);
    void saveOrUpdate(Message message);
}
