package com.bhn.raptorauthorizationservice;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "raptor.platform.security")
@Data
public class SecurityProperties {


    // TODO: 8/21/2022 Need to file the key in a secret locker instead of hardcoding
    @Value("${jwt.secret}")
    private String secret;

    // the below is the list of endpoint patterns that can be declared for not restricting it which is comma seperated
    private String unRestricted;
}