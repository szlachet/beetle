package com.szlachet.beetle.security.auth.boundary;

import com.szlachet.beetle.security.auth.control.JsonWebTokenGenerator;
import com.szlachet.beetle.security.auth.control.RsaKeyGenerator;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jose4j.jwk.JsonWebKeySet;

/**
 *
 * @author Sebastian Szlachetka
 */
@Stateless
public class AuthTokenBoundary {
    private static final Logger LOGGER = Logger.getLogger(AuthTokenBoundary.class.getName());
    @Inject
    private JsonWebTokenGenerator jwtGen;

    @Inject
    private RsaKeyGenerator rsaKeyGenerator;
    
    public String getJsonWebToken() {
        return jwtGen.generateJsonWebToken();
    }

    public JsonWebKeySet getJsonWebKeySet() {
        return rsaKeyGenerator.getJsonwebKeySet();
    }
}
