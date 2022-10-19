package com.zemelya.controller.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCreateRequest {

    private String userName;

    private String surname;

    private Timestamp birthDate;

    private String login;

    private String password;

    private String email;

}
