package com.zemelya.controller.request.car;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CarChangeRequest extends CarCreateRequest {
  @Schema(example = "123", required = true)
  private Long id;
}
