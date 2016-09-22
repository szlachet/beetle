package com.szlachet.beetle.security.resource.jwt;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sebastian Szlachetka
 */
@Path("/unsecure")
public class UnSecureResource {

    @GET
    public Response getGreetings() {
        JsonObject jsonGreetings = Json.createObjectBuilder().add("greetings", "Hello unsecure resources :)").build();
        return Response.ok(jsonGreetings).build();
    }
}
