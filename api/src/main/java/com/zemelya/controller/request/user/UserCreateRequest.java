package com.zemelya.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Schema(description = "Create user object without system info")
public class UserCreateRequest {

    @Schema(example = "name", required = true, minLength = 1, maxLength = 20)
    @Size(min = 2, max = 20)
    @NotBlank
    private String userName;

    @Schema(example = "surname", required = true, minLength =12, maxLength = 50)
    @Size(min = 2, max = 50)
    @NotBlank
    private String surname;

    @Schema(example = "2022-10-21T16:08:39.673Z", required = true)
    private Timestamp birthDate;

    @Schema(example = "userLogin", required = true, minLength = 2, maxLength = 20)
    @Size(min = 2, max = 20)
    private String login;

    @Schema(example = "defaultPassword", required = true, minLength = 5, maxLength = 50)
    @Size(min = 5, max = 50)
    private String password;

    @Schema(example = "email@gmail.com", required = true, minLength = 5, maxLength = 30)
    @Size(min = 5, max = 30)
    @Email
    @NotBlank
    private String email;

}
