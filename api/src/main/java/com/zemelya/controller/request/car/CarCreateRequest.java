package com.zemelya.controller.request.car;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create car object without system info")
public class CarCreateRequest {
  @Schema(example = "1", required = true, minLength = 1)
  private Integer modelId;

  @Schema(example = "2.0", required = true, minLength = 1)
  private Double volume;

  @Schema(example = "123123DFFFFF465646FG4", required = true, minLength = 1, maxLength = 30)
  @Size(min = 1, max = 30)
  @NotBlank
  private String vinNumber;

  @Schema(example = "26.30", required = true, minLength = 1)
  private Double price;
}
