package com.zemelya.controller.request.drivingLicenses;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class DrivingLicenseChangeRequest extends DrivingLicenseCreateRequest {

  @Hidden
  @Min(value = 0)
  private Long id;
}
