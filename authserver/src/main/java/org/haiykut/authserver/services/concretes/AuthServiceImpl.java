package org.haiykut.authserver.services.concretes;

import lombok.RequiredArgsConstructor;
import org.haiykut.authserver.core.services.JwtService;
import org.haiykut.authserver.core.utils.exceptions.types.AuthenticationExceptionBase;
import org.haiykut.authserver.entities.User;
import org.haiykut.authserver.services.abstracts.AuthService;
import org.haiykut.authserver.services.abstracts.UserService;
import org.haiykut.authserver.services.dtos.requests.LoginRequest;
import org.haiykut.authserver.services.dtos.requests.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        //Global exception handler eklendi, kullanici adi/sifre yanlislarinda ayni kalipta hatalar firlatilmasi saglandi.
        //Code refactor yapildi.
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        }
        catch (AuthenticationException e){
            throw new AuthenticationExceptionBase("Kullanici adi ya da sifre yanlis!");
        }
        return jwtService.generateTokenWithClaims(userService.loadUserByUsername(loginRequest.getEmail()));
    }
}
