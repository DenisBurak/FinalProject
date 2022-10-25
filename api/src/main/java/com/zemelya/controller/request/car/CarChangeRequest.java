package com.zemelya.controller.request.car;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class CarChangeRequest extends CarCreateRequest {
  @Schema(example = "1", required = true)
  @Min(value = 1)
  @Max(value = Long.MAX_VALUE)
  private Long id;
}
