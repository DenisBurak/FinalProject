package com.zemelya.controller.request.user;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserChangeRequest extends UserCreateRequest {

  @Schema(example = "123", required = true)
  private Long id;

  @Hidden
  @Size(min = 0)
  private String login;

  @Hidden
  @Size(min = 0)
  private String password;
}
