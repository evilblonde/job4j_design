package tdd.parking;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParkingLotTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private String info(int bigSpots, int smallSpots, int bigSpotSize) {
        return String.format("Parking lot has %d big and %d regular spots (1 big spot = %d regular spots)",
                bigSpots, smallSpots, bigSpotSize);
    }

    @Test
    public void whenParkingIsEmptyThenCarPlaced() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(3, 10, 3);
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
        Vehicle smallVehicle = new SmallCar("x109ca99");
        Ticket ticket = parkingLot.park(smallVehicle);
        assertEquals(info(3, 9, 3), parkingLot.getSpotInfo());
        assertEquals(smallVehicle, parkingLot.takeBack(ticket));
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenNoSpotsAvailableThenDoNotPark() throws Exception {
        ParkingLot parkingLot = createFullParking();
        assertEquals(info(0, 0, 3), parkingLot.getSpotInfo());
        Vehicle smallVehicle = new SmallCar("x109ca99");
        parkUnsuccessfully(parkingLot, smallVehicle);
    }

    @Test
    public void whenOnlySmallSpotsLeftThenParkBigVehicle() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(3, 4, 3); // size 7, add some cars there
        parkingLot.park(new BigCar(3, "eu789o87"));
        parkingLot.park(new BigCar(3, "eu790o87"));
        parkingLot.park(new BigCar(3, "eu791o87"));
        assertEquals(info(0, 4, 3), parkingLot.getSpotInfo());
        Vehicle bigVehicle = new BigCar(3, "eu792o87"); //create vehicle with size 3
        assertTrue(parkingLot.hasPlaceFor(bigVehicle));
        Ticket ticket = parkingLot.park(bigVehicle);
        assertEquals(info(0, 1, 3), parkingLot.getSpotInfo());
        assertEquals(bigVehicle, parkingLot.takeBack(ticket));
        assertEquals(info(0, 4, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenSomeSpotsAreEmptyButNoSpotForBigVehicleThenDoNotPark() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(0, 5, 3); //create almost full parking but without spots next to each other to park a truck
        Ticket ticket1 = parkingLot.park(new SmallCar("a123op45"));
        Ticket ticket2 = parkingLot.park(new SmallCar("a122op45"));
        parkingLot.park(new SmallCar("a121op45"));
        parkingLot.takeBack(ticket1);
        parkingLot.takeBack(ticket2);
        assertEquals(info(0, 4, 3), parkingLot.getSpotInfo());
        Vehicle bigVehicle = new BigCar(3, "ot002a22");
        parkUnsuccessfully(parkingLot, bigVehicle);
    }

    @Test
    public void whenBigSpotsAvailableThenParkBigVehiclesThere() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(3, 10, 3);
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
        Vehicle bigVehicle = new BigCar(3, "ot002a22");
        Ticket ticket = parkingLot.park(bigVehicle);
        assertEquals(info(2, 10, 3), parkingLot.getSpotInfo());
        assertEquals(bigVehicle, parkingLot.takeBack(ticket));
        assertEquals(info(3, 10, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenBigPlacesOnlyAvailableThenDoNotParkSmallCars() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(3, 1, 3);
        parkingLot.park(new SmallCar("a121op45"));
        assertEquals(info(3, 0, 3), parkingLot.getSpotInfo());
        Vehicle smallVehicle = new SmallCar("a122op45");
        parkUnsuccessfully(parkingLot, smallVehicle);
    }

    @Test
    public void whenSeveralCarsComeAndGoThenParkThem() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(0, 1, 3);
        assertEquals(info(0, 1, 3), parkingLot.getSpotInfo());
        parkSuccessfully(parkingLot, new SmallCar("bp223e09")); //new Vehicle
        parkSuccessfully(parkingLot, new SmallCar("bp224e09"));
        parkSuccessfully(parkingLot, new SmallCar("bp225e09"));
        assertEquals(info(0, 1, 3), parkingLot.getSpotInfo());
    }

    @Test
    public void whenSuperBigVehicleComesThenDoNotParkIt() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(2, 3, 4);
        assertEquals(info(2, 3, 4), parkingLot.getSpotInfo());
        Vehicle vehicle = new BigCar(5, "hh222h76");//create vehicle with size 5
        assertFalse(parkingLot.hasPlaceFor(vehicle));
        parkUnsuccessfully(parkingLot, vehicle);
    }

    private void parkSuccessfully(ParkingLot parkingLot, Vehicle vehicle) throws Exception {
        assertTrue(parkingLot.hasPlaceFor(vehicle));
        Ticket ticket = parkingLot.park(vehicle);
        assertEquals(vehicle, parkingLot.takeBack(ticket));
    }

    private void parkUnsuccessfully(ParkingLot parkingLot, Vehicle vehicle) throws Exception {
        assertFalse(parkingLot.hasPlaceFor(vehicle));
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Parking is full!");
        parkingLot.park(vehicle);
    }

    //some code that creates parking and fills it with cars
    private ParkingLot createFullParking() throws Exception {
        ParkingLot parkingLot = new ParkingLotImpl(2, 2, 3);
        parkingLot.park(new SmallCar("a123op45"));
        parkingLot.park(new SmallCar("a122op45"));
        parkingLot.park(new BigCar(3, "a113op45"));
        parkingLot.park(new BigCar(3, "a223op45"));
        return parkingLot;
    }
}