package com.zemelya.controller.request.user;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class UserChangeRequest extends UserCreateRequest {

  @Hidden
  @Min(value = 0)
  private Long id;

  @Hidden
  @Size(min = 0)
  private String login;

  @Hidden
  @Size(min = 0)
  private String password;
}
