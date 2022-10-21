package com.zemelya.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Generate token by user name and password")
public class AuthRequest {

    @Schema(example = "login", required = true, minLength = 2, maxLength = 20)
    private String login;

    @Schema(example = "password", required = true, minLength = 5, maxLength = 200)
    private String password;
}
