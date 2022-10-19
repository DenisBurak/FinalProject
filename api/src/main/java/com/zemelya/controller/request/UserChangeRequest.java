package com.zemelya.controller.request;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
public class UserChangeRequest extends UserCreateRequest {
    private Long id;
}
