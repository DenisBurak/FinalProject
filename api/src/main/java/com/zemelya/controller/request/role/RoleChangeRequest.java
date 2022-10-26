package com.zemelya.controller.request.role;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class RoleChangeRequest extends RoleCreateRequest {

  @Hidden
  @Min(value = 0)
  private Integer id;
}
