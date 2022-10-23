package com.zemelya.controller.request.model;

import com.zemelya.controller.request.brand.BrandCreateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ModelChangeRequest extends ModelCreateRequest {
  @Schema(example = "123", required = true)
  private Integer id;
}
