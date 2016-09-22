package com.szlachet.beetle.security.infrastructure.common;

/**
 *
 * @author sszlach
 */
public abstract class AssertionSubject {
    
    protected void assertNotEmpty(String aString, String aMessage) {
        if(aString == null && aString.trim().isEmpty()) {
            throw new IllegalArgumentException(aMessage);
        }
    }
}
