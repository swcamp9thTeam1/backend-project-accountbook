package com.iiiiii.accountbook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/health")
    public String healthCheck() {
        return "어서오세요!!";
    }
}
