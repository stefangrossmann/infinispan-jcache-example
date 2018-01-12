package stefan.demo.jcache.control;

import org.infinispan.Cache;
import stefan.demo.jcache.entity.Message;

import javax.inject.Inject;
import java.util.UUID;

public class DemoCachedService {
    @Inject
    private Cache<String, Message> cache;

    public Message getMessage(String to) {
        Message message = cache.get(to);
        if (message == null) {
            final String text = String.format("Hello %s! [%s]", to, UUID.randomUUID().toString());
            message = new Message(text);
            cache.putForExternalRead(to, message);
        }
        return message;
    }
}