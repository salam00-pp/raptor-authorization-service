package com.bhn.raptorauthorizationservice.controller;


import com.bhn.raptorauthorizationservice.jwt.JwtUtils;
import com.bhn.raptorauthorizationservice.model.AuthRequest;
import com.bhn.raptorauthorizationservice.models.User;
import com.bhn.raptorauthorizationservice.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shahbaz Alam
 */

@Slf4j
@RestController
@RequestMapping("${api.prefix}")

public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest, HttpServletRequest request){
        String token=jwtTokenProvider.generateToken(authRequest);

        return ResponseEntity.ok("Token generated successfully :"+token);
    }
    @GetMapping("/verify-token/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable String token)
    {

        return ResponseEntity.ok(jwtUtils.validateJwtToken(token));
    }


}
