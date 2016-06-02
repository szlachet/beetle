package com.szlachet.beetle.security.auth.control;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
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
    private RsaJsonWebKey rsaJsonWebKey;
    
    @PostConstruct
    private void initRsaJsonWebKey() {
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            rsaJsonWebKey.setKeyId("BeetleKeyId01");
            LOGGER.log(Level.INFO, "Public key: {0}", rsaJsonWebKey.getPublicKey().getEncoded());
            LOGGER.log(Level.INFO, "Private key: {0}", rsaJsonWebKey.getPrivateKey().getEncoded());
            
        } catch (JoseException ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
    }
    
    //TODO build Json Web Token using jose4j 
    public String generateJsonWebToken() {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("beetle");
        claims.setAudience("beetle");
        claims.setExpirationTimeMinutesInTheFuture(5);
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        claims.setSubject("admin");
        claims.setClaim("email", "sebastian.szlachetka@gmail.com");
        claims.setStringListClaim("groups", Arrays.asList("group-one", "group-two"));
        
        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_PSS_USING_SHA256);
        String jwt = "";
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
            LOGGER.log(Level.SEVERE, "", ex);
        }
        return jwt;
    } 
}
