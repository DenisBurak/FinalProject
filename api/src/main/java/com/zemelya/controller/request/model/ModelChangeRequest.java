package com.zemelya.controller.request.model;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ModelChangeRequest extends ModelCreateRequest {

  @Hidden
  @Min(value = 0)
  private Integer id;
}
