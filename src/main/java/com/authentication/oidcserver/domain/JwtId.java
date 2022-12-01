package com.authentication.oidcserver.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtId {
    private String id_token;
}
