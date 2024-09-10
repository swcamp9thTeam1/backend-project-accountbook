package com.iiiiii.accbookserver.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${jasypt.encryptor.password}")
    private String jPassword;

    @Value("${jasypt.encryptor.algorithm}")
    private String jAlgorithm;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(jPassword);
        encryptor.setAlgorithm(jAlgorithm);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(encryptor.decrypt(url
                    .replace("ENC(", "")
                    .replace(")", "")));
        dataSource.setUsername(encryptor.decrypt(username
                    .replace("ENC(", "")
                    .replace(")", "")));
        dataSource.setPassword(encryptor.decrypt(password
                    .replace("ENC(", "")
                    .replace(")", "")));
        return dataSource;
    }
}
