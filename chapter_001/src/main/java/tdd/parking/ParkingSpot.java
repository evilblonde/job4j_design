package tdd.parking;

public interface ParkingSpot {

    int getSize();

    void setOccupiedWith(Vehicle vehicle);

    void remove(Vehicle vehicle);

    boolean isOccupied();

    ParkingSpot getNeighbor();

}
