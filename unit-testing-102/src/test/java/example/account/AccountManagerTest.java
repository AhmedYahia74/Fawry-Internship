package example.account;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class AccountManagerTest {
    Customer customer = new Customer();
    private static AccountManager accountManager = null;

    @BeforeAll
    public static void init() {
        accountManager = new AccountManagerImpl();
    }

    @Test
    public void givenValidAmount_whenDeposit_thenSucceed() {
        accountManager.deposit(customer, 50);
        assertThat(customer.getBalance()).isEqualTo(50);
    }

    @Test
    public void givenANotValidAmount_whenDeposit_thenFailed() {
        accountManager.deposit(customer, -50);
        assertThat(customer.getBalance()).isEqualTo(0);
    }

    @Test
    public void givenValidAmount_whenWithdraw_thenSucceed() {
        // arrange
        customer.setBalance(100);

        // act
        String result = accountManager.withdraw(customer, 50);

        // assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(50);
    }

    @Test
    public void givenANotValidAmountWithCreditAvailable_whenWithdraw_thenSucceed() {
        // arrange
        customer.setBalance(40);
        customer.setCreditAllowed(true);

        // act
        String result = accountManager.withdraw(customer, 50);

        // assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(-10);
    }

    @Test
    public void givenANotValidAmountWithVIPAvailable_whenWithdraw_thenSucceed() {
        // arrange
        customer.setBalance(40);
        customer.setVip(true);

        // act
        String result = accountManager.withdraw(customer, 500);

        // assert
        assertThat(result).isEqualTo("success");
        assertThat(customer.getBalance()).isEqualTo(-460);
    }

    @Test
    public void givenANotValidAmount_whenWithdraw_thenFailed() {
        // arrange
        customer.setBalance(40);

        // act
        String result = accountManager.withdraw(customer, 50);

        // assert
        assertThat(result).isEqualTo("insufficient account balance");
        assertThat(customer.getBalance()).isEqualTo(40);
    }

    @Test
    public void givenAnAmountExceedMaxCredit_whenWithdraw_thenFailed() {
        // arrange
        customer.setBalance(40);
        customer.setCreditAllowed(true);

        // act
        String result = accountManager.withdraw(customer, 5000000);

        // assert
        assertThat(result).isEqualTo("maximum credit exceeded");
        assertThat(customer.getBalance()).isEqualTo(40);
    }

}
