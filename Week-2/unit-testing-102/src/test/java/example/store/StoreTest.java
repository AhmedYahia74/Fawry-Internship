package example.store;

import example.account.AccountManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class StoreTest {
    private AccountManager accountManager;
    private Product product;
    private Store store;

    @BeforeEach
    public void setup() {
        product = new Product();
        accountManager = Mockito.mock(AccountManager.class);
        store = new StoreImpl(accountManager);
    }

    @Test
    public void givenValidProductAndCustomer_whenBuy_thenSucceed() {
        // arrange
        product.setQuantity(1);
        Mockito.when(accountManager.withdraw(null, product.getPrice())).thenReturn("success");

        // act
        store.buy(product, null);

        // assert
        assertThat(product.getQuantity()).isEqualTo(0);
        Mockito.verify(accountManager).withdraw(ArgumentMatchers.isNull(), ArgumentMatchers.anyInt());
    }

    @Test
    public void givenANotValidProductQuantity_whenBuy_thenThrowException() {
        //arrange
        product.setQuantity(0);

        //act
        Throwable thrown = catchThrowable(() -> store.buy(product, null));

        //assert
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("Product out of stock");

        Mockito.verify(accountManager, Mockito.never()).withdraw(ArgumentMatchers.isNull(), ArgumentMatchers.anyInt());
    }

    @Test
    public void givenANotValidCustomerBalance_whenBuy_thenThrowException() {
        //arrange
        product.setQuantity(1);
        Mockito.when(accountManager.withdraw(ArgumentMatchers.isNull(), ArgumentMatchers.anyInt())).
                thenReturn("X");
        //act
        Throwable thrown = catchThrowable(() -> store.buy(product, null));

        //assert
        assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessageContaining("Payment failure: X");

        Mockito.verify(accountManager).withdraw(ArgumentMatchers.isNull(), ArgumentMatchers.anyInt());
    }

}
