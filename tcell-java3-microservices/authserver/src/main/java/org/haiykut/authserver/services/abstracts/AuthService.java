package org.haiykut.authserver.services.abstracts;

import org.haiykut.authserver.services.dtos.requests.LoginRequest;
import org.haiykut.authserver.services.dtos.requests.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    String login(LoginRequest loginRequest);
}