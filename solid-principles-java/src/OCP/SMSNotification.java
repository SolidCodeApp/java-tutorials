package OCP;

public class SMSNotification implements NotificationChannel {

    @Override
    public void send() {
        System.out.println("Notification has been sent via SMS!");
    }

}
