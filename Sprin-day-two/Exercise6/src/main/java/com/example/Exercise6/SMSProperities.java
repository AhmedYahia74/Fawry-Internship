package com.example.Exercise6;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms")
@Data
public class SMSProperities {
    private String provider;
    private String accountSid;
    private String authToken;
    private String fromNumber;
}
