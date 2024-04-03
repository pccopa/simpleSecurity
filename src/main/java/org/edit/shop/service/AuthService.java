package org.edit.shop.service;

import org.edit.shop.entity.User;
import org.edit.shop.models.LoginRequest;
import org.edit.shop.models.SignupRequest;

public interface AuthService {
    String login (LoginRequest login);
    User signup (SignupRequest signup);
}
