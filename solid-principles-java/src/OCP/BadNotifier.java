package OCP;

public class BadNotifier {

    public void send(String channel) {
        if (channel.toUpperCase().equals("EMAIL")) {
            System.out.println("Notification has been sent via email!");
        } else if (channel.toUpperCase().equals("SMS")) {
            System.out.println("Notification has been sent via SMS!");
        } else if (channel.toUpperCase().equals("PUSH")) {
            System.out.println("Notification has been pushed!");
        } else {
            throw new IllegalArgumentException("Channel unkown!");
        }
    }

}