package stefan.demo.jcache.control;

import stefan.demo.jcache.entity.Message;

import javax.cache.annotation.CacheResult;
import javax.enterprise.context.Dependent;
import java.util.UUID;

@Dependent
public class DemoCachedService {

    @CacheResult
    public Message getMessage(String to) {
        final String text = String.format("Hello %s! [%s]", to, UUID.randomUUID().toString());
        return new Message(text);
    }
}