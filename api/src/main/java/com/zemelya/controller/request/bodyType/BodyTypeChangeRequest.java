package com.zemelya.controller.request.bodyType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BodyTypeChangeRequest extends BodyTypeCreateRequest {
  @Schema(example = "123", required = true)
  private Integer id;
}
