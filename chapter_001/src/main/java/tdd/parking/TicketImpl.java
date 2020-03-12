package tdd.parking;

public class TicketImpl implements Ticket {

    private final ParkingSpot[] spots;

    public TicketImpl(ParkingSpot... spots) {
        this.spots = spots;
    }

    public ParkingSpot[] getSpots() {
        return spots;
    }
}
