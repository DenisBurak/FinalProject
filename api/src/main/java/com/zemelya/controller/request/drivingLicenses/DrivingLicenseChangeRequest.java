package com.zemelya.controller.request.drivingLicenses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class DrivingLicenseChangeRequest extends DrivingLicenseCreateRequest {
  @Schema(example = "1", required = true)
  @Min(value = 1)
  @Max(value = Long.MAX_VALUE)
  private Long id;
}
