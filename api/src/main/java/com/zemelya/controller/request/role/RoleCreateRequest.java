package com.zemelya.controller.request.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Create role object without system info")
public class RoleCreateRequest {

    @Schema(example = "name", required = true, minLength = 1, maxLength = 20)
    @Size(min = 1, max = 20)
    @NotBlank
    private String roleName;

}
