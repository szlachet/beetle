package com.szlachet.beetle.security.domain.model.jwt;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.lang.JoseException;

/**
 *
 * @author Sebastian Szlachetka
 */
@ApplicationScoped
public class RsaKeyGenerator {
    
    private static final Logger LOGGER = Logger.getLogger(RsaKeyGenerator.class.getName());
    private final JsonWebKeySet jsonWebKeySet = new JsonWebKeySet();
    private RsaJsonWebKey rsaJsonWebKey;
    
    @PostConstruct
    private void initRsaJsonWebKey() {
        generateRsaJsonWebKey();
        jsonWebKeySet.addJsonWebKey(rsaJsonWebKey);
    }
    
    public JsonWebKeySet getJsonwebKeySet() {
        return jsonWebKeySet;
    }
    
    public RsaJsonWebKey getRsaJsonWebKey() {
        return rsaJsonWebKey;
    }
    
    private void generateRsaJsonWebKey() {
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            rsaJsonWebKey.setKeyId(UUID.randomUUID().toString());
            rsaJsonWebKey.setAlgorithm(AlgorithmIdentifiers.HMAC_SHA256);
            LOGGER.log(Level.SEVERE, "RSA JSON Web Key: {0}", rsaJsonWebKey.toJson());
        } catch (JoseException ex) {
            LOGGER.log(Level.SEVERE, "RSA key pair could not be generated.", ex);
        }
    }
}
