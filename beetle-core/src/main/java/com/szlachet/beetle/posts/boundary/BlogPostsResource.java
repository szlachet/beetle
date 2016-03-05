package com.szlachet.beetle.posts.boundary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

/**
 *
 * @author Sebastian Szlachetka
 */
@Path("/posts")
@Api
public class BlogPostsResource {
    
    @Inject
    private BlogPostsBoundary pb;
    
    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getPost(@PathParam("id") String id) {
        String jsonPost = pb.getPost(id);
        return Response.ok(jsonPost).build();
    }
}
