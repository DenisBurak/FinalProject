package com.zemelya.controller.request.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BrandChangeRequest extends BrandCreateRequest {
  @Schema(example = "123", required = true)
  private Integer id;
}
