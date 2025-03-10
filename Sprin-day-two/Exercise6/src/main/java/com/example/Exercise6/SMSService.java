package com.example.Exercise6;

import org.springframework.stereotype.Service;

@Service
public class SMSService {
    private final SMSProperities smsProperities;


    public SMSService(SMSProperities smsProperities) {
        this.smsProperities = smsProperities;
    }

    void sendMessage(String to){

    }
}
