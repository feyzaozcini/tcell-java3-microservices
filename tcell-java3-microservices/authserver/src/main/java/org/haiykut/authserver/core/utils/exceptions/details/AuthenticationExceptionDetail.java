package org.haiykut.authserver.core.utils.exceptions.details;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationExceptionDetail {
    private String title = "Authentication Error - 401";
    private String message;
}
