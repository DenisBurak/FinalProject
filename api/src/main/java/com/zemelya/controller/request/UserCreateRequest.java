package com.zemelya.controller.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Hidden
public class UserCreateRequest {

    private String userName;

    private String surname;

    private Timestamp birthDate;

    private String login;

    private String password;

    private String email;

}
