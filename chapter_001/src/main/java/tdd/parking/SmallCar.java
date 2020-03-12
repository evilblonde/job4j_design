package tdd.parking;

public class SmallCar implements Vehicle {

    private final String id;
    private static final int SIZE = 1;

    public SmallCar(String id) {
        this.id = id;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getId() {
        return id;
    }
}
