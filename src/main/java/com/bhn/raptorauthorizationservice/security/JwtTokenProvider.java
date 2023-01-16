package com.bhn.raptorauthorizationservice.security;


import com.bhn.raptorauthorizationservice.model.AuthRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Shahbaz Alam
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider implements Serializable {

        private static final long serialVersionUID = -2550185165626007488L;

        public static final long JWT_TOKEN_VALIDITY = 60;

        @Value("${jwt.secret}")
        private String secret;

        private SecretKey secretKey;

        @PostConstruct
        public void init()
        {
            String secretVal = Base64.getEncoder().encodeToString(this.secret.getBytes());
            this.secretKey= Keys.hmacShaKeyFor(secretVal.getBytes(StandardCharsets.UTF_8));

        }
        public String getUsernameFromToken(String token) {
            return getClaimFromToken(token, Claims::getSubject);
        }

        public Date getIssuedAtDateFromToken(String token) {
            return getClaimFromToken(token, Claims::getIssuedAt);
        }

        public Date getExpirationDateFromToken(String token) {
            return getClaimFromToken(token, Claims::getExpiration);
        }

        public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }

        private Claims getAllClaimsFromToken(String token) {
            return Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody();
        }

        private Boolean isTokenExpired(String token) {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

        private Boolean ignoreTokenExpiration(String token) {
            // here you specify tokens, for that the expiration is ignored
            return false;
        }

        public String generateToken(AuthRequest authRequest) {
            Map<String, Object> claims = new HashMap<>();
            return doGenerateToken(claims, authRequest.getUserName());
        }

        private String doGenerateToken(Map<String, Object> claims, String subject) {

            return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(secretKey,SignatureAlgorithm.HS512).compact();
        }

        public Boolean canTokenBeRefreshed(String token) {
            return (!isTokenExpired(token) || ignoreTokenExpiration(token));
        }

        public Boolean validateToken(String token, AuthRequest authRequest) {
            final String username = getUsernameFromToken(token);
            return (username.equals(authRequest.getUserName()) && !isTokenExpired(token));
        }
}
