package com.zemelya.controller.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;

    private String username;
}
