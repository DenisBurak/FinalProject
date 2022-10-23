package com.zemelya.controller.request.rentalAgreement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RentalAgreementChangeRequest extends RentalAgreementCreateRequest {
  @Schema(example = "123", required = true)
  private Long id;
}
