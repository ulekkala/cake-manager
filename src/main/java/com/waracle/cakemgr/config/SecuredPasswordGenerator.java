package com.waracle.cakemgr.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class SecuredPasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "CakeManager";
        String encodedPassword = encoder.encode(rawPassword);
        log.info(encodedPassword);
    }
}
