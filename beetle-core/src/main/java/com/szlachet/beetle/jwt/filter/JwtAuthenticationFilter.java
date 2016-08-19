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
package com.szlachet.beetle.jwt.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

/**
 *
 * @author Sebastian Szlachetka
 */
@Provider
@JwtAuthenticated
@Priority(Priorities.AUTHENTICATION)
public class JwtAuthenticationFilter implements ContainerRequestFilter {

    private static final String BEARER_TOKEN = "Bearer";

    private static final Logger LOGGER = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    private HttpsJwks jwks;

    @PostConstruct
    private void init() {
        //TODO it should be resolved in the better way
        jwks = new HttpsJwks("http://localhost:8080/beetle-security/resources/authentication/keys");
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null) {
            verifyJsonWebToken(parseBearerToken(authorizationHeader));
        } else {
            throw new NotAuthorizedException("No authorization header found.", Response.status(Response.Status.UNAUTHORIZED));
        }
    }

    private String parseBearerToken(String authorizationHeader) {
        if (authorizationHeader.isEmpty() || !authorizationHeader.startsWith(BEARER_TOKEN)) {
            throw new NotAuthorizedException("No bearer token found.", Response.status(Response.Status.UNAUTHORIZED));
        }
        return retrieveJsonWebToken(authorizationHeader);
    }

    private String retrieveJsonWebToken(String authorizationHeader) {
        return Arrays.stream(authorizationHeader.split(" "))
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new NotAuthorizedException("No bearer token found.",
                        Response.status(Response.Status.UNAUTHORIZED)));
    }

    private void verifyJsonWebToken(String jwt) {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setMaxFutureValidityInMinutes(300) // but the  expiration time can't be too crazy
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setRequireSubject() // the JWT must have a subject claim
                .setExpectedIssuer("beetle") // whom the JWT needs to have been issued by
                .setExpectedAudience("beetle")
                .setVerificationKeyResolver(new HttpsJwksVerificationKeyResolver(jwks)).build();
        try {
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            LOGGER.log(Level.INFO, "Json Web Token verification success: {0}.", jwtClaims);
        } catch (InvalidJwtException ex) {
            throw new NotAuthorizedException("Invalid Json Web Token!", Response.status(Response.Status.UNAUTHORIZED), ex);
        }

    }

}
