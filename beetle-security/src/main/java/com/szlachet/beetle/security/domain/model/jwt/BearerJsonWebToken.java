package com.szlachet.beetle.security.domain.model.jwt;

import com.szlachet.beetle.security.infrastructure.common.AssertionSubject;

/**
 *
 * @author Sebastina Szlachetka
 */
public class BearerJsonWebToken extends AssertionSubject {
    
    private static final String BEARER_TOKEN = "Bearer";
    
    private String jsonWebToken;

    public BearerJsonWebToken(String aJsonWebToken) {
        this.setJsonWebToken(aJsonWebToken);
    }

    public String jsonWebToken() {
        return this.jsonWebToken;
    }
    
    private void setJsonWebToken(String aJsonWebToken) {
        this.assertNotEmpty(aJsonWebToken, "The bearer json web token can be neither null nor empty.");
        this.jsonWebToken = String.join(" ", BEARER_TOKEN, aJsonWebToken);
    }
    
}
