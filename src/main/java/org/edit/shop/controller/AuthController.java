package org.edit.shop.controller;

import org.edit.shop.entity.User;
import org.edit.shop.models.LoginRequest;
import org.edit.shop.models.SignupRequest;
import org.edit.shop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    AuthService service;

    @PostMapping("login")
    public String login (@RequestBody LoginRequest login) {
        return service.login(login);
    }

    @PostMapping("signup")
    public ResponseEntity<User> signup (@RequestBody SignupRequest signup) {
        try {
            return ResponseEntity.ok(service.signup(signup));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Autowired
    public void setService(AuthService service) {
        this.service = service;
    }


//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public String handleDataIntegrityViolation(DataIntegrityViolationException ex) {
//        return "Error: Ya existe un registro con este ID en la base de datos.";
//    }
}
