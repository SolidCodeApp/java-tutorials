package OCP;

public class EmailNotification implements NotificationChannel {

    @Override
    public void send() {
        System.out.println("Notification has been sent via email!");
    }

}
