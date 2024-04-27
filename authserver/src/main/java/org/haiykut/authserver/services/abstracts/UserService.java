package org.haiykut.authserver.services.abstracts;
import org.haiykut.authserver.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void add(User user);
}
