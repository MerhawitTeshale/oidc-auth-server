package com.authentication.oidcserver.util;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenUtil  implements Serializable {

    private static final int JWT_TOKEN_VALIDITY =  5 * 60 * 60 ;

//    @Value("${jwt.secret}")
    private String secret = "MERHAWIT";


     public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
