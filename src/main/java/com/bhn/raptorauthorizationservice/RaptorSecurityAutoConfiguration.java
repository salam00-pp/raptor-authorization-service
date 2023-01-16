package com.bhn.raptorauthorizationservice;

import com.bhn.raptorauthorizationservice.jwt.AuthEntryPointJwt;
import com.bhn.raptorauthorizationservice.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@ConditionalOnClass(com.bhn.raptorauthorizationservice.WebSecurityConfig.class)
@EnableConfigurationProperties(SecurityProperties.class)
@Slf4j
public class RaptorSecurityAutoConfiguration {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public String RaptorSecurityAuth(){
        log.info("Raptor security auth enabled successfully !!!! {}", new Date());
        return "Raptor security auth initiated successfully.";
    }

}
