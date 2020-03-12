package tdd.parking;

public class BigParkingSpot extends SmallParkingSpot {

    private final int size;

    public BigParkingSpot(int id, int size) {
        super(id);
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
