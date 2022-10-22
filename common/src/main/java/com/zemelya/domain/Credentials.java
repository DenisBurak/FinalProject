package com.zemelya.domain;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Hidden
public class Credentials {

//    @Size(min = 2, max = 20)
//    @NotBlank
    private String login;

//    @Size(min = 5, max = 50)
//    @NotBlank
    private String password;
}
