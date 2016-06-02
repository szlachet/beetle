package com.szlachet.beetle.security.auth.boundary;

import com.szlachet.beetle.security.filter.BasicAuthenticated;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sebastian Szlachetka
 */
@Path("/authentication")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@ServletSecurity(@HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.NONE, rolesAllowed = {"admin"} ))
public class AuthTokenResource {

    @Inject
    private AuthTokenBoundary authToken;
    
    @GET
    @BasicAuthenticated
    public Response getJsonWebToken() {
        
        JsonObject jsonGreetings = Json.createObjectBuilder().add("token", authToken.getJsonWebToken()).build();
        return Response.ok(jsonGreetings).build();
    }
    
}
