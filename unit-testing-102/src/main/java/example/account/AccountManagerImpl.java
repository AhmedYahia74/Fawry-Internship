package example.account;

public class AccountManagerImpl implements AccountManager {
    private static final int MAX_CREDIT = -1000;
    @Override
    public void deposit(Customer customer, int amount) {
        if(amount<0)
            return;
        customer.setBalance(customer.getBalance() + amount);
    }

    @Override
    public String withdraw(Customer customer, int amount) {
        int expectedBalance = customer.getBalance() - amount;

        if (expectedBalance < 0 && !customer.isVip()) {
            if (!customer.isCreditAllowed()) {
                return "insufficient account balance";
            } else if (expectedBalance < MAX_CREDIT) {
                return "maximum credit exceeded";
            }
        }
        customer.setBalance(expectedBalance);
        return "success";
    }
}
