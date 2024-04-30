package org.haiykut.authserver.core.utils.exceptions.types;
public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message){
        super(message);
    }
}
