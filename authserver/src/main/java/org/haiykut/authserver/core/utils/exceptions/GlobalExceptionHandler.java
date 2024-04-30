package org.haiykut.authserver.core.utils.exceptions;

import org.haiykut.authserver.core.utils.exceptions.details.AuthenticationExceptionDetail;
import org.haiykut.authserver.core.utils.exceptions.types.AuthenticationExceptionBase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AuthenticationExceptionBase.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationExceptionDetail handleAuthenticationException(AuthenticationExceptionBase exception){
        AuthenticationExceptionDetail details = new AuthenticationExceptionDetail();
        details.setMessage(exception.getMessage());
        return details;
    }
}
