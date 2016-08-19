package com.szlachet.beetle.security.auth.control;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Sebastian Szlachetka
 */
@ApplicationScoped
public class JsonWebTokenGenerator {
    private static final Logger LOGGER = Logger.getLogger(JsonWebTokenGenerator.class.getName());
    
    @Inject 
    private RsaKeyGenerator rsaKeyGenerator;
    
    public String generateJsonWebToken() {
        final RsaJsonWebKey rsaJsonWebKey = rsaKeyGenerator.getRsaJsonWebKey();
        final JwtClaims claims = new JwtClaims();
        claims.setIssuer("beetle");
        claims.setAudience("beetle");
        claims.setExpirationTimeMinutesInTheFuture(5);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setSubject("admin");
        claims.setClaim("email", "sebastian.szlachetka@gmail.com");
        claims.setStringListClaim("groups", Arrays.asList("group-one", "group-two"));
        
        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
        String jwt = "";
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
            // TODO it should be handle in the better way than just logging
            LOGGER.log(Level.INFO, "Json Web Token generation failed!", ex);
        }

        return jwt;
    } 
}
