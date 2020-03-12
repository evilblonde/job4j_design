package tdd.parking;

public interface ParkingSpot {

    int getSize();

    void setOccupiedWith(Vehicle vehicle);

    Vehicle remove();

    boolean isOccupied();

}
