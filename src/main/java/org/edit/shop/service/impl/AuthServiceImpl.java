package org.edit.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.edit.shop.config.TokenService;
import org.edit.shop.entity.User;
import org.edit.shop.models.LoginRequest;
import org.edit.shop.models.SignupRequest;
import org.edit.shop.repository.UserRepository;
import org.edit.shop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final TokenService tokenService;

    public String login(LoginRequest login) {
        User usr = repository.findByUsername(login.getUsername()).orElseThrow();
        return tokenService.generate(usr.getUsername());
    }

    @Override
    public User signup(SignupRequest signup) {
        User user = new User ();
        user.setPassword(signup.getPassword());
        user.setUsername(signup.getUsername());
        user.setBirthDate(signup.getBirthDate());
        user.setName(signup.getName());
        return repository.save(user);
    }
}
