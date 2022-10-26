package com.zemelya.controller.request.bodyType;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BodyTypeChangeRequest extends BodyTypeCreateRequest {

  @Hidden
  @Min(value = 0)
  private Integer id;
}
