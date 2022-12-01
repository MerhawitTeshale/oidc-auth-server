package com.authentication.oidcserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class AuthorizationRequest {
    private String redirect_uri;
    private String response_type;
    private String response_mode;
    private String code_challenge;
    private String code_challenge_method;
    private String scope;
}
