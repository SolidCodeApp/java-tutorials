import DIP.CreditCardPayment;
import DIP.PaymentProcessor;
import DIP.PaypalPayment;
import LSP.BankAccount;
import LSP.LimitExceedException;
import LSP.SavingsAccount;

public class App {

    /**
     * ROADMAP
     * 1. Single Responsibility Principle
     * 2. Open / Close Principle
     * 3. Liskov Substitution Principle
     * 4. Interface Segregation Principle
     * 5. Dependency Inversion Principle
     */
    public static void main(String[] args) throws Exception {

        PaypalPayment paypalPayment = new PaypalPayment();

        PaymentProcessor paymentProcessor = new PaymentProcessor(paypalPayment);

        paymentProcessor.process(200);

        BankAccount account = new SavingsAccount(500);
        try {
            account.withdraw(600);
        } catch (LimitExceedException exception) {
            System.out.println("Mon coco, tu n'as pas assez d'argent!");
        }

        System.out.println("Notre application n'a pas planté! La vie continue!");

    }
}