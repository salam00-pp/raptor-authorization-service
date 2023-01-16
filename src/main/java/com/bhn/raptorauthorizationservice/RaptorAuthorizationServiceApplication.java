package com.bhn.raptorauthorizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaptorAuthorizationServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RaptorAuthorizationServiceApplication.class, args);
        //System.out.println(generateSafeToken());


    }
//    public static String generateSafeToken() {
//        SecureRandom random = new SecureRandom();
//        byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
//        // the 256 required bits
//        random.nextBytes(bytes);
//        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
//        return encoder.encodeToString(bytes);
//    }
}
