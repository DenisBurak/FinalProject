package com.zemelya.controller.responce;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Returns generated token and user name")
public class AuthResponse {

    private String token;

    private String username;
}
