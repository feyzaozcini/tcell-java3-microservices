package org.haiykut.authserver.services.concretes;

import lombok.RequiredArgsConstructor;
import org.haiykut.authserver.entities.User;
import org.haiykut.authserver.repositories.UserRepository;
import org.haiykut.authserver.services.abstracts.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new BadCredentialsException(""));
    }
}
