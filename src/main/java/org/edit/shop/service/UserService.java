package org.edit.shop.service;

import org.edit.shop.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll ();
    User findById (Long id);


}
