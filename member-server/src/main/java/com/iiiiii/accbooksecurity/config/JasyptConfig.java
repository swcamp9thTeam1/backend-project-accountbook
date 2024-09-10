package com.iiiiii.accbooksecurity.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String jPassword;

    @Value("${jasypt.encryptor.algorithm}")
    private String jAlgorithm;

    @Bean
    public StandardPBEStringEncryptor standardPBEStringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(jPassword);
        encryptor.setAlgorithm(jAlgorithm);
        return encryptor;
    }
}
