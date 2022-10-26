package com.zemelya.controller.request.brand;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BrandChangeRequest extends BrandCreateRequest {

  @Hidden
  @Min(value = 0)
  private Integer id;
}
