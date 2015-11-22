package com.szlachet.beetle.business.posts.boundary;

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
public class PostsResource {
    
    @Inject
    private PostsBoundary pb;
    
    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getPost(@PathParam("id") String id) {
        String jsonPost = pb.getPost(id);
        return Response.ok(jsonPost).build();
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllPosts() {
        List<String> posts = pb.getAllPosts();
        return Response.ok(posts).build();
    }
    
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addPost(JsonObject post) {
        String id = pb.addPost(post);
        return Response.created(URI.create("/posts/" + id)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response removePost(@PathParam("id") String id) {
        pb.removePost(id);
        return Response.status(NO_CONTENT).build();
    }
}
