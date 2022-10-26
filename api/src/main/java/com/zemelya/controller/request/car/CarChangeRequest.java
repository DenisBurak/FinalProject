package com.zemelya.controller.request.car;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CarChangeRequest extends CarCreateRequest {

  @Hidden
  @Min(value = 0)
  private Long id;
}
