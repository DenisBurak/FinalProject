package com.zemelya.controller.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserChangeRequest extends UserCreateRequest {

    @Schema(example = "user ID", required = true)
    private Long id;
}
