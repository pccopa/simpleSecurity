package org.edit.shop.models;

import lombok.Data;

import java.util.Date;

@Data
public class SignupRequest {

    private String username;
    private String password;
    private String name;
    private Date birthDate;

}
