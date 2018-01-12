package stefan.demo.jcache.boundary;

import org.infinispan.Cache;
import stefan.demo.jcache.entity.Message;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("demo")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class DemoResource {
    @Inject
    private Cache<String, String> cache;

    @GET
    @Path("cached/uuid")
    public Response get() {
        String text = cache.get("uuid");
        if (text == null) {
            text = UUID.randomUUID().toString();
            cache.putForExternalRead("uuid", text);
        }

        final Message message = new Message(text);
        return Response.ok().entity(message).build();
    }
}