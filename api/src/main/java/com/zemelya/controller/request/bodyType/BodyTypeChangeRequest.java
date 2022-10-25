package com.zemelya.controller.request.bodyType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class BodyTypeChangeRequest extends BodyTypeCreateRequest {
  @Schema(example = "1", required = true)
  @Min(value = 1)
  @Max(value = Integer.MAX_VALUE)
  private Integer id;
}
