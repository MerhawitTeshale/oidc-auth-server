package com.authentication.oidcserver.service;

import com.authentication.oidcserver.domain.DiscoveryDocument;
import com.authentication.oidcserver.domain.JwksPojo;
import com.authentication.oidcserver.domain.JwksPublicKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@AllArgsConstructor
@Data
@Service
@Slf4j
public class DiscoveryDocumentService {
  private final DiscoveryDocument discoveryDocument;

  public DiscoveryDocument discoveryDocumentCreator() {
      DiscoveryDocument discoveryDocument = new DiscoveryDocument();
      discoveryDocument.setIssuer("http://localhost:8080");
      discoveryDocument.setAuthorization_endpoint("http://localhost:8080/authorize");
      discoveryDocument.setToken_endpoint("http://localhost:8080/token");
      discoveryDocument.setJwks_uri("http://localhost:8080/jwks.json");
      discoveryDocument.setScopes_supported(Arrays.asList("openid"));
      return discoveryDocument;
  }

  public JwksPublicKey getJwksPublicKey() throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      JwksPojo keys = objectMapper.readValue(new File("src/main/java/jwks_uri.json"), JwksPojo.class);
      JwksPublicKey jwksPublicKey = new JwksPublicKey();
      jwksPublicKey.setKeys(Arrays.asList(keys));
      return jwksPublicKey;
  }

  public String authorize (String codeChallenger)  {
//      AuthorizationRequest authorizationRequest = new AuthorizationRequest();
//      authorizationRequest.setRedirect_uri(redirect_uri);
//      authorizationRequest.setResponse_type(response_type);
//      authorizationRequest.setResponse_mode(response_mode);
//      authorizationRequest.setCode_challenge_method(code_challenge_method);
//      authorizationRequest.setScope(scope);
//      authorizationRequest.setCode_challenge(code_challenge);
      UUID authCode = UUID.randomUUID();
      try {
          FileWriter fileWriter = new FileWriter("src/main/java/codeChallenge.txt");
          fileWriter.write(authCode.toString());
          fileWriter.close();
      } catch (IOException e ){
          log.info("DDS: IOException", e.getMessage());
      }
      return authCode.toString();
  }
}
