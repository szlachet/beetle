package com.szlachet.beetle.security.auth.boundary;

import com.szlachet.beetle.security.filter.BasicAuthenticated;
import javax.inject.Inject;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
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
public class AuthTokenResource {

    private static final String BEARER_TOKEN = "Bearer";

    @Inject
    private AuthTokenBoundary authToken;

    @GET
    @Path("/token")
    @BasicAuthenticated
    public Response getJsonWebToken() {
        return Response.ok().header(HttpHeaders.AUTHORIZATION, String.join(" ", BEARER_TOKEN, authToken.getJsonWebToken())).build();
    }

    @GET
    @Path("/keys")
    public Response getJsonWebKeySet() {
        return Response.ok(authToken.getJsonWebKeySet().toJson()).build();
    }
}
