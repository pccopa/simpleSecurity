package org.edit.shop.controller;

import org.edit.shop.entity.User;
import org.edit.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    UserService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_NORMAL_USER')")
    public List<User> findAll () {
        return service.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User findById (@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities().stream().map(x->x.getAuthority()).reduce("",(a,b)->a+b));
        return service.findById(id);
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }
}
