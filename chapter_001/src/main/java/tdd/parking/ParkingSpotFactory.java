package tdd.parking;

public class ParkingSpotFactory {

    private static int id = 0;

    public static SmallParkingSpot createSmallSpot() {
        return new SmallParkingSpot(++id);
    }

    public static BigParkingSpot createBigSpot(int size) {
        return new BigParkingSpot(++id, size);
    }

}
