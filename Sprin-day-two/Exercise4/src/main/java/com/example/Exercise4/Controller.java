package com.example.Exercise4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {
    @Value("${spring.application.name: GG}")
    String prop;
    @GetMapping("api")
    public String getValue(){
        return prop;
    }

}
