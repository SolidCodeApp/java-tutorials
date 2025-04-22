package LSP;

public class SavingsAccount extends BankAccount {

    private static double WITHDRAW_LIMIT = 200;

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) throws LimitExceedException {
        if (amount > WITHDRAW_LIMIT) {
            throw new LimitExceedException("Compte épargne _ limite escédé");
        }
        super.withdraw(amount);
    }

    /*
     * @Override
     * public void withdraw(double amount) {
     * if (amount > WITHDRAW_LIMIT) {
     * throw new IllegalArgumentException("Compte épargne _ limite escédé");
     * }
     * super.withdraw(amount);
     * }
     */

}
