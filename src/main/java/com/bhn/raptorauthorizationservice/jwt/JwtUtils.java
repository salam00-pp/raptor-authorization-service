package com.bhn.raptorauthorizationservice.jwt;

import com.bhn.raptorauthorizationservice.SecurityProperties;
import com.bhn.raptorauthorizationservice.commons.Constants;
import com.bhn.raptorauthorizationservice.models.Role;
import com.bhn.raptorauthorizationservice.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  private SecretKey secretKey;

  @Autowired
  private SecurityProperties securityProperties;

  @PostConstruct
  public void init() {
    String secret = Base64.getEncoder().encodeToString(securityProperties.getSecret().getBytes());
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public User getUserFromJwtToken(String token) {
    return User.builder()
            .name(Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody().get(Constants.Jwt.PAYLOAD_ATTRIBUTE_NAME, String.class))
            .id(5675456765L)
            .email(Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody().get(Constants.Jwt.PAYLOAD_ATTRIBUTE_NAME, String.class))
            .roles(new HashSet<>(Collections.singleton(Role.valueOf(Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody().get(Constants.Jwt.PAYLOAD_ATTRIBUTE_ROLES, String.class)))))
            .build();
  }

  // TODO: 8/20/2022 Need to handle all jwt exceptions and respond it to client with the proper message
  public boolean validateJwtToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

}
