package DIP;

public class CreditCardPayment implements PaymentMethod {

    @Override
    public void processPayment(double amount) {
        System.out.println("Le paiement a été éffectué via carte de crédit: " + amount);
    }

}
