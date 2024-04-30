package org.haiykut.authserver.services.concretes;

import lombok.RequiredArgsConstructor;
import org.haiykut.authserver.core.services.JwtService;
import org.haiykut.authserver.core.utils.exceptions.details.UsernameOrPasswordErrorDetails;
import org.haiykut.authserver.core.utils.exceptions.types.AuthenticationException;
import org.haiykut.authserver.entities.User;
import org.haiykut.authserver.services.abstracts.AuthService;
import org.haiykut.authserver.services.abstracts.UserService;
import org.haiykut.authserver.services.dtos.requests.LoginRequest;
import org.haiykut.authserver.services.dtos.requests.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.add(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if(!authentication.isAuthenticated()){
            //Global exception handler eklendi.
            throw new AuthenticationException("Username Or Password is wrong!");
        }
        //Refactor edildi.
        return jwtService.generateTokenWithClaims(userService.loadUserByUsername(loginRequest.getEmail()));
    }


}
