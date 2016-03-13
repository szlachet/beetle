package com.szlachet.beetle.posts.boundary;

import com.szlachet.beetle.posts.entity.Post;
import io.swagger.annotations.Api;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.bson.types.ObjectId;

/**
 *
 * @author Sebastian Szlachetka
 */
@Path("/posts")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Api
public class PostsResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private PostsBoundary pb;
    
    @GET
    @Path("/{id}")
    public Response getPost(@PathParam("id") String id) {
        Post post = pb.getPost(new ObjectId(id));
        return post == null ? Response.status(NOT_FOUND).build() : Response.ok(post).build();
    }

    @POST
    public Response createPost(Post aPost) {
        String id = pb.createPost(aPost);
        return Response.created(UriBuilder.fromUri(uriInfo.getBaseUri()).path(PostsResource.class).path("/{id}").build(id)).build();
    }
}
