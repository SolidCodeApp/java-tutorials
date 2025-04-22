package ISP;

public class Car implements Vehicule, Motorized {

    @Override
    public void move() {
        System.out.println("The Car is moving...");
    }

    @Override
    public void startEngine() {
        System.out.println("Engine is being started!");
    }

}
