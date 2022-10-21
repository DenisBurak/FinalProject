package com.zemelya.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Schema(description = "Create user object without system info")
public class UserCreateRequest {

    @Schema(example = "name", required = true, minLength = 1, maxLength = 20)
    private String userName;

    @Schema(example = "surname", required = true, minLength =12, maxLength = 50)
    private String surname;

    @Schema(example = "2022-10-21T16:08:39.673Z", required = true)
    private Timestamp birthDate;

    @Schema(example = "userLogin", required = true, minLength = 2, maxLength = 20)
    @Size(min = 2, max = 20)
    @NotNull
    private String login;

    @Schema(example = "defaultPassword", required = true, minLength = 5, maxLength = 200)
    private String password;

    @Schema(example = "email@gmail.com", required = true, minLength = 5, maxLength = 30)
    private String email;

}
