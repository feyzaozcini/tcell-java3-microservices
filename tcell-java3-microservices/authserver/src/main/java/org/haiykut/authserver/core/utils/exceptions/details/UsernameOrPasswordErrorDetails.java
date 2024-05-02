package org.haiykut.authserver.core.utils.exceptions.details;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsernameOrPasswordErrorDetails {
    private String title = "Authentication Error!";
    private String message;
}
