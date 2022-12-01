package com.authentication.oidcserver.domain;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwksPublicKey {
   List<JwksPojo> keys;
}
