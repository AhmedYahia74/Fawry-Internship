package com.example.exercise5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawService {
    private final BalanceService balanceService;

    public WithdrawService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public Boolean withdraw(Account account, double amount) {
        if (balanceService.checkBalance(account, amount)) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }
}
