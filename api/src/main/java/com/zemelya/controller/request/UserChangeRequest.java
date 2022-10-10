package com.zemelya.controller.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
@Hidden
public class UserChangeRequest extends UserCreateRequest {
    private Long id;
}
