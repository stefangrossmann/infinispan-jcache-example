package stefan.demo.jcache.boundary;

import stefan.demo.jcache.control.DemoCachedService;
import stefan.demo.jcache.entity.Message;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("demo")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Stateless
public class DemoResource {
    @Inject
    private DemoCachedService demoCachedService;

    @GET
    @Path("cached/uuid")
    public Response get(@QueryParam("to") @DefaultValue("world") String to) {
        final Message message = demoCachedService.getMessage(to);
        return Response.ok().entity(message).build();
    }
}