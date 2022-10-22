package com.zemelya.controller.request.drivingLicenses;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Schema(description = "Create driving license object without system info")
public class DrivingLicenseCreateRequest {
  @Schema(example = "Divine Toyota", required = true, minLength = 3, maxLength = 20)
  @Size(min = 3, max = 20)
  @NotBlank
  private String brandName;

  @Schema(example = "1214FG14", required = true, minLength = 3, maxLength = 30)
  @Size(min = 3, max = 30)
  private String serialNumber;

  @Schema(example = "2022-10-21T16:08:39.673Z", required = true)
  private Timestamp dateOfIssue;

  @Schema(example = "2022-10-21T16:08:39.673Z", required = true)
  @Size(min = 3, max = 20)
  private Timestamp expirationDate;

  @Schema(example = "A,B,C,D", required = true, minLength = 1, maxLength = 15)
  @Size(min = 1, max = 15)
  private String category;

  @Hidden
  private Long userId;
}
