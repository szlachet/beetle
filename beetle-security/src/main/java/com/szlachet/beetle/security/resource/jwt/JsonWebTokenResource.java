package com.szlachet.beetle.security.resource.jwt;

import com.szlachet.beetle.security.application.jwt.JsonWebTokenService;
import com.szlachet.beetle.security.infrastructure.jaxrs.filter.BasicAuthenticated;
import javax.inject.Inject;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;

/**
 *
 * @author Sebastian Szlachetka
 */
@Path("/authentication")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@ServletSecurity(
        @HttpConstraint(transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL, rolesAllowed = {"admin"}))
public class JsonWebTokenResource {

    @Inject
    private JsonWebTokenService jsonWenTokenService;
    
    @GET
    @Path("/token")
    @BasicAuthenticated
    public Response getJsonWebToken() {
        String jsonWebToken =
                this.jsonWenTokenService
                        .getBearerJsonWebToken()
                        .jsonWebToken();
        Response response = Response.ok().header(AUTHORIZATION, jsonWebToken).build();
        return response;
    }

    @GET
    @Path("/keys")
    public Response getJsonWebKeySet() {
        return Response.ok(jsonWenTokenService.getJsonWebKeySet().toJson()).build();
    }
}
