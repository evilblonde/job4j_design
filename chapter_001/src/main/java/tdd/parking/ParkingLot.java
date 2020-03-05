package tdd.parking;

public interface ParkingLot {

    boolean hasPlaceFor(Vehicle vehicle);

    Ticket park(Vehicle vehicle);

    Vehicle takeBack(Ticket ticket);

    String getSpotInfo();

}
