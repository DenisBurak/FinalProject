package com.zemelya.controller.request.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create brand object without system info")
public class BrandCreateRequest {
    @Schema(example = "Divine Toyota", required = true, minLength = 3, maxLength = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String brandName;
}
