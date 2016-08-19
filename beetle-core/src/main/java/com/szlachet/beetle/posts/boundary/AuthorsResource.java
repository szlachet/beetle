/*
 * Copyright 2016 Sebastian Szlachetka.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.szlachet.beetle.posts.boundary;

import com.szlachet.beetle.jwt.filter.JwtAuthenticated;
import com.szlachet.beetle.posts.entity.Author;
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
@Path("/authors")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Api
@JwtAuthenticated
public class AuthorsResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private AuthorsBoundary ab;

    @GET
    @Path("/{id}")
    public Response getAuthor(@PathParam("id") String id) {
        Author author = ab.getAuthor(new ObjectId(id));
        return author == null ? Response.status(NOT_FOUND).build() : Response.ok(author).build();
    }

    @GET
    public Response getAuthors() {
        return Response.ok(ab.getAuthors()).build();
    }

    @POST
    public Response createAuthor(Author aAuthor) {
        String id = ab.createAuthor(aAuthor);
        return Response.created(UriBuilder.fromUri(uriInfo.getBaseUri()).path(AuthorsResource.class).path("/{id}").build(id)).build();
    }
}
