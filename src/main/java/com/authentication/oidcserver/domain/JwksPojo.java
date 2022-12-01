package com.authentication.oidcserver.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwksPojo {
    String crv;
    String ext;
    List<String> key_ops;
    String kid;
    String kty;
    String x;
    String y;
}
