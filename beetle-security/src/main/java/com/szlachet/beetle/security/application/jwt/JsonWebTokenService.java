package com.szlachet.beetle.security.application.jwt;

import com.szlachet.beetle.security.domain.model.jwt.BearerJsonWebToken;
import com.szlachet.beetle.security.domain.model.jwt.JsonWebTokenGenerator;
import com.szlachet.beetle.security.domain.model.jwt.RsaKeyGenerator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.jose4j.jwk.JsonWebKeySet;

/**
 *
 * @author Sebastian Szlachetka
 */
@Stateless
public class JsonWebTokenService {

    @Inject
    private JsonWebTokenGenerator jwtGen;

    @Inject
    private RsaKeyGenerator rsaKeyGenerator;
    
    public BearerJsonWebToken getBearerJsonWebToken() {
        String jsonWebToken = jwtGen.generateJsonWebToken();
        BearerJsonWebToken bearerJsonWebToken =
                new BearerJsonWebToken(jsonWebToken);
        
        return bearerJsonWebToken;
    }

    public JsonWebKeySet getJsonWebKeySet() {
        return rsaKeyGenerator.getJsonwebKeySet();
    }
}
