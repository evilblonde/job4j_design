package tdd.parking;

public interface ParkingLot {

    boolean hasPlaceFor(Vehicle vehicle);

    Ticket park(Vehicle vehicle) throws Exception;

    Vehicle takeBack(Ticket ticket) throws Exception;

    String getSpotInfo();

}
