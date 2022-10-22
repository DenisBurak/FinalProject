package com.zemelya.controller.request.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleChangeRequest extends RoleCreateRequest {

  @Schema(example = "123", required = true)
  private Integer id;
}
