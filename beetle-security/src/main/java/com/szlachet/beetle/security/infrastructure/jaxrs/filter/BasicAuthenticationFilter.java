package com.szlachet.beetle.security.infrastructure.jaxrs.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Sebastian Szlachetka
 */
@Provider
@BasicAuthenticated
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthenticationFilter implements ContainerRequestFilter {

    private static final String ADMIN = "admin";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_AUTHENTICATION = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        if (isBasicAuthentication(authHeader)) {
            authenticate(requestContext, authHeader);
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }

    private boolean isBasicAuthentication(String authHeader) {
        return authHeader != null && authHeader.startsWith(BASIC_AUTHENTICATION);
    }

    private void authenticate(final ContainerRequestContext requestContext, final String authHeader) {
        String[] decodedCredentials = decodeAuthorisationHeader(authHeader);
        if (isAuthenticated(decodedCredentials)) {
            requestContext.setSecurityContext(getSecurityContext(requestContext, decodedCredentials));
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthenticated(String[] decodedCredentials) {
        boolean isAuthenticated = false;
        if (decodedCredentials.length == 2) {
            final String username = decodedCredentials[0];
            final String password = decodedCredentials[1];
            isAuthenticated = username.equals(ADMIN) && password.equals(ADMIN);
        }
        return isAuthenticated;
    }

    private String[] decodeAuthorisationHeader(String authHeader) {
        final String encodedCredentials = authHeader.replaceFirst(BASIC_AUTHENTICATION, "");
        String[] decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials)).split(":");
        return decodedCredentials;
    }

    private SecurityContext getSecurityContext(ContainerRequestContext requestContext, String[] decodedCredentials) {
        final SecurityContext securityContext = requestContext.getSecurityContext();
        return new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return decodedCredentials[0];
                    }
                };
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return securityContext.isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return securityContext.getAuthenticationScheme();
            }
        };
    }

}
