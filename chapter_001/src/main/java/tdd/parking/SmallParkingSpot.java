package tdd.parking;

import java.util.Objects;

public class SmallParkingSpot implements ParkingSpot {

    private static final int SIZE = 1;
    private final int spotId;
    private Vehicle vehicle;

    public SmallParkingSpot(int spotId) {
        this.spotId = spotId;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public void setOccupiedWith(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public Vehicle remove() {
        Vehicle v = vehicle;
        this.vehicle = null;
        return v;
    }

    @Override
    public boolean isOccupied() {
        return vehicle != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SmallParkingSpot that = (SmallParkingSpot) o;
        return spotId == that.spotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotId);
    }
}
