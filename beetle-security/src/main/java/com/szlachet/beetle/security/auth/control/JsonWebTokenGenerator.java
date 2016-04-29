package com.szlachet.beetle.security.auth.control;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Sebastian Szlachetka
 */
@ApplicationScoped
public class JsonWebTokenGenerator {
    
    //TODO build Json Web Token using jose4j 
    public String generateJsonWebToken() {
        return "dummy Json Web Token";
    } 
}
