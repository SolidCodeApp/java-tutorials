package DIP;

public class PaypalPayment implements PaymentMethod {

    @Override
    public void processPayment(double amount) {
        System.out.println("Paiemnt éffectué par paypal! Montant : " + amount);
    }

}
