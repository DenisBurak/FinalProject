package com.zemelya.controller.request.rentalAgreement;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class RentalAgreementChangeRequest extends RentalAgreementCreateRequest {

  @Hidden
  @Min(value = 0)
  private Long id;
}
