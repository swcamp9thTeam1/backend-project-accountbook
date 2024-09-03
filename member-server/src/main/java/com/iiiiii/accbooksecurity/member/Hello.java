package com.iiiiii.accbooksecurity.member;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Hello {

    @Value("${hello.message}")
    private String message;
}