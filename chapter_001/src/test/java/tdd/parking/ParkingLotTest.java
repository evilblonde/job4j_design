package tdd.parking;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

@Ignore
public class ParkingLotTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private String info(int bigSpots, int smallSpots, int bigSpotSize) {
        return String.format("Parking lot has %d big and %d regular spots (1 big spot = %d regular spots)",
                bigSpots, smallSpots, bigSpotSize);
    }

    @Test
    public void whenParkingIsEmptyThenCarPlaced() {
        ParkingLot parkingLot = null;
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
        Vehicle smallVehicle = null;
        Ticket ticket = parkingLot.park(smallVehicle);
        assertEquals(info(3, 9, 3), parkingLot.getSpotInfo());
        assertEquals(smallVehicle, parkingLot.takeBack(ticket));
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenNoSpotsAvailableThenDoNotPark() {
        ParkingLot parkingLot = createFullParking();
        assertEquals(info(0, 0, 3), parkingLot.getSpotInfo());
        Vehicle smallVehicle = null;
        parkUnsuccessfully(parkingLot, smallVehicle);
    }

    @Test
    public void whenOnlySmallSpotsLeftThenParkBigVehicle() {
        ParkingLot parkingLot = null; // size 7, add some cars there
        assertEquals(info(0, 4, 3), parkingLot.getSpotInfo());
        Vehicle bigVehicle = null; //create vehicle with size 3
        assertTrue(parkingLot.hasPlaceFor(bigVehicle));
        Ticket ticket = parkingLot.park(bigVehicle);
        assertEquals(info(0, 1, 3), parkingLot.getSpotInfo());
        assertEquals(bigVehicle, parkingLot.takeBack(ticket));
        assertEquals(info(0, 4, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenSomeSpotsAreEmptyButNoSpotForBigVehicleThenDoNotPark() {
        ParkingLot parkingLot = null; //create almost full parking but without spots next to each other to park a truck
        assertEquals(info(0, 4, 3), parkingLot.getSpotInfo());
        Vehicle bigVehicle = null;
        parkUnsuccessfully(parkingLot, bigVehicle);
    }

    @Test
    public void whenBigSpotsAvailableThenParkBigVehiclesThere() {
        ParkingLot parkingLot = null;
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
        Vehicle bigVehicle = null;
        Ticket ticket = parkingLot.park(bigVehicle);
        assertEquals(info(2, 10, 3), parkingLot.getSpotInfo());
        assertEquals(bigVehicle, parkingLot.takeBack(ticket));
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenBigPlacesOnlyAvailableThenDoNotParkSmallCars() {
        ParkingLot parkingLot = null;
        assertEquals(info(3, 0, 3), parkingLot.getSpotInfo());
        Vehicle smallVehicle = null;
        parkUnsuccessfully(parkingLot, smallVehicle);
    }

    @Test
    public void whenSeveralCarsComeAndGoThenParkThem() {
        ParkingLot parkingLot = null;
        assertEquals(info(0, 1, 3), parkingLot.getSpotInfo());
        parkSuccessfully(parkingLot, null); //new Vehicle
        parkSuccessfully(parkingLot, null);
        parkSuccessfully(parkingLot, null);
        assertEquals(info(0, 1, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenSuperBigVehicleComesThenParkIt() {
        ParkingLot parkingLot = null;
        assertEquals(info(1, 3, 3), parkingLot.getSpotInfo());
        Vehicle vehicle = null;//create vehicle with size 5
        assertTrue(parkingLot.hasPlaceFor(vehicle));
        Ticket ticket = parkingLot.park(vehicle);
        assertEquals(info(0, 1, 3), parkingLot.getSpotInfo());
        assertEquals(vehicle, parkingLot.takeBack(ticket));
        assertEquals(info(1, 3, 3), parkingLot.getSpotInfo());
    }

    private void parkSuccessfully(ParkingLot parkingLot, Vehicle vehicle) {
        assertTrue(parkingLot.hasPlaceFor(vehicle));
        Ticket ticket = parkingLot.park(vehicle);
        assertEquals(vehicle, parkingLot.takeBack(ticket));
    }

    private void parkUnsuccessfully(ParkingLot parkingLot, Vehicle vehicle) {
        assertFalse(parkingLot.hasPlaceFor(vehicle));
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Parking is full!");
        parkingLot.park(vehicle);
    }

    //some code that creates parking and fills it with cars
    private ParkingLot createFullParking() {
        return null;
    }
}