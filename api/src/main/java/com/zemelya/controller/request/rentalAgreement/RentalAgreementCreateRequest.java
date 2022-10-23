package com.zemelya.controller.request.rentalAgreement;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(description = "Create rental agreement object without system info")
public class RentalAgreementCreateRequest {
  @Schema(example = "1", required = true, minLength = 1)
  private Long userId;

  @Schema(example = "1", required = true, minLength = 1)
  private Long carId;

  @Schema(example = "50.20", required = true, minLength = 1)
  private Double totalCost;

  @Schema(example = "2022-10-21T16:08:39.673Z", required = true)
  private Timestamp rentalStartDate;

  @Schema(example = "2022-10-21T16:08:39.673Z", required = true)
  private Timestamp expirationDate;
}
