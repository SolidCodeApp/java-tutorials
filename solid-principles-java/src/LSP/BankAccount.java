package LSP;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) throws LimitExceedException {
        if (amount > this.balance) {
            throw new LimitExceedException("Montant demandé supérieur à la balance de votre compte!");
        }

        this.balance -= amount;
    }

}
