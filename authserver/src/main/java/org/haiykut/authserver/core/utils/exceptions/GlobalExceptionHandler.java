package org.haiykut.authserver.core.utils.exceptions;

import org.haiykut.authserver.core.utils.exceptions.details.UsernameOrPasswordErrorDetails;
import org.haiykut.authserver.core.utils.exceptions.types.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public UsernameOrPasswordErrorDetails handleAuthenticationException(AuthenticationException exception){
        UsernameOrPasswordErrorDetails details = new UsernameOrPasswordErrorDetails();
        details.setMessage(exception.getMessage());
        return details;
    }
}
