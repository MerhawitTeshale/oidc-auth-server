package com.authentication.oidcserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class DiscoveryDocument {
    private String issuer;
    private String authorization_endpoint;
    private String token_endpoint;
    private String jwks_uri;
    private List<String> scopes_supported;
}
