package com.szlachet.beetle.security.auth.boundary;

import com.szlachet.beetle.security.auth.control.JsonWebTokenGenerator;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Szlachetka
 */
@Stateless
public class AuthTokenBoundary {
    
    @Inject
    private JsonWebTokenGenerator jwtGen;
    
    public String getJsonWebToken() {
        return jwtGen.generateJsonWebToken();
    }
}
