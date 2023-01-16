package com.bhn.raptorauthorizationservice.controller;


import com.bhn.raptorauthorizationservice.model.AuthRequest;
import com.bhn.raptorauthorizationservice.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest, HttpServletRequest request){
        String token=jwtTokenProvider.generateToken(authRequest);

        return ResponseEntity.ok("Token generated successfully :"+token);
    }

}
