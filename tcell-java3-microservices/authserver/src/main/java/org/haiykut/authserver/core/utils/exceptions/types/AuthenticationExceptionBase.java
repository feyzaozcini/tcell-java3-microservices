package org.haiykut.authserver.core.utils.exceptions.types;


public class AuthenticationExceptionBase extends RuntimeException {
    public AuthenticationExceptionBase(String message){
        super(message);
    }
}
