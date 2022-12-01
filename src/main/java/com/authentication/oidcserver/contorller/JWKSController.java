package com.authentication.oidcserver.contorller;

import com.authentication.oidcserver.domain.*;
import com.authentication.oidcserver.service.DiscoveryDocumentService;
import com.authentication.oidcserver.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping()
@Slf4j
public class JWKSController {

    @Autowired
    private DiscoveryDocumentService service;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/.well-known/openid-configuration")
    public ResponseEntity<?> grabJWKS() throws IOException {
        DiscoveryDocument discoveryDocument = service.discoveryDocumentCreator();
        return new ResponseEntity<>(discoveryDocument, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/jwks.json")
    public ResponseEntity<?> getJwksPublicKey() throws IOException {
        JwksPublicKey jwksPublicKey = service.getJwksPublicKey();
        return new ResponseEntity<>(jwksPublicKey, HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/authorize")
    public ResponseEntity<Void> authorize(@RequestParam String redirect_uri,
                                       @RequestParam String response_type,
                                       @RequestParam String response_mode,
                                       @RequestParam String code_challenge,
                                       @RequestParam String code_challenge_method,
                                       @RequestParam String scope
                                       ) throws IOException {
    String code = service.authorize(code_challenge);
    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirect_uri+"?code="+code)).build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/token")
    ResponseEntity<?> tokenEndpoint() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("hello", "new Object()");
        String subject = "subject";
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//        jwtTokenUtil.doGenerateToken(claims, subject);
        JwtId jwtId = new JwtId(jwtTokenUtil.doGenerateToken(claims, subject));

        return new ResponseEntity<>(jwtId, HttpStatus.OK);
    }

}
