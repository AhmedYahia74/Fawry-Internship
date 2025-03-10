package com.example.exercise5;

import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    public Boolean checkBalance(Account account, double amount){
        return account.getBalance()>=amount;
    }
}
