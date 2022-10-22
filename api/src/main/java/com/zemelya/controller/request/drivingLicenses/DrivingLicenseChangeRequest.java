package com.zemelya.controller.request.drivingLicenses;

import com.zemelya.controller.request.brand.BrandCreateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DrivingLicenseChangeRequest extends DrivingLicenseCreateRequest {
  @Schema(example = "123", required = true)
  private Long id;
}
