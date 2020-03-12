package tdd.parking;

public class BigCar implements Vehicle {

    private final String id;
    private final int size;

    public BigCar(int size, String id) {
        this.size = size;
        this.id = id;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getId() {
        return id;
    }
}
