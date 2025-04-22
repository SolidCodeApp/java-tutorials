package OCP;

public class PushNotification implements NotificationChannel {

    @Override
    public void send() {
        System.out.println("Notification has been pushed!");
    }

}
