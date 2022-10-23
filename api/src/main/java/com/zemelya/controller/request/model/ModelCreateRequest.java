package com.zemelya.controller.request.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create model object without system info")
public class ModelCreateRequest {
    @Schema(example = "Carina", required = true, minLength = 3, maxLength = 30)
    @Size(min = 1, max = 30)
    @NotBlank
    private String modelName;

    @Schema(example = "1", required = true, minLength = 1)
    private Integer brandId;

    @Schema(example = "1", required = true, minLength = 1)
    private Integer bodyTypeId;
}
