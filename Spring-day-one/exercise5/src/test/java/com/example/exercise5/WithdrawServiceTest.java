package com.example.exercise5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WithdrawServiceTest {
    @Autowired
    WithdrawService withdrawService;
    @Test
    void ShouldReturnTrue(){
        Account account=new Account(50);
        Boolean res=withdrawService.withdraw(account,40);
        assertThat(account.getBalance()).isEqualTo(10);
        assertThat(res).isTrue();
    }
}
