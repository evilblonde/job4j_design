package tdd.parking;

import java.util.*;

public class ParkingLotImpl implements ParkingLot {

    private final Set<ParkingSpot> allSpots = new LinkedHashSet<>();
    private final Set<ParkingSpot> freeSmallSpots = new HashSet<>();
    private final Set<ParkingSpot> freeBigSpots = new HashSet<>();
    private int bigSpotSize;

    public ParkingLotImpl(int bigAmount, int smallAmount, int bigSpotSize) {
        for (int i = 0; i < bigAmount; i++) {
            freeBigSpots.add(ParkingSpotFactory.createBigSpot(bigSpotSize));
        }
        for (int i = 0; i < smallAmount; i++) {
            freeSmallSpots.add(ParkingSpotFactory.createSmallSpot());
        }
        allSpots.addAll(freeBigSpots);
        allSpots.addAll(freeSmallSpots);
        this.bigSpotSize = bigSpotSize;
    }

    @Override
    public boolean hasPlaceFor(Vehicle vehicle) {
        if (vehicle.getSize() > bigSpotSize
                || (freeBigSpots.isEmpty() && freeSmallSpots.isEmpty())
                || (vehicle.getSize() == 1 && freeSmallSpots.isEmpty())) {
            return false;
        }
        if ((vehicle.getSize() == 1 && freeSmallSpots.size() > 0)
                || (vehicle.getSize() <= bigSpotSize && freeBigSpots.size() > 0)) {
            return true;
        }
        return getExtendedSpot(vehicle.getSize()).isPresent();
    }

    private Optional<ParkingSpot[]> getExtendedSpot(int spotSize) {
        int index = 0;
        ParkingSpot[] extendedSpot = new ParkingSpot[spotSize];
        for (ParkingSpot parkingSpot : allSpots) {
            if (!parkingSpot.isOccupied()) {
                extendedSpot[index++] = parkingSpot;
                if (index == spotSize) {
                    return Optional.of(extendedSpot);
                }
            } else {
                Arrays.fill(extendedSpot, null);
                index = 0;
            }
        }
        return Optional.empty();
    }

    @Override
    public Ticket park(Vehicle vehicle) throws Exception {
        int vehicleSize = vehicle.getSize();
        if (vehicleSize == 1 && !freeSmallSpots.isEmpty()) {
            ParkingSpot spot = getFirstAvailableSpot(freeSmallSpots);
            spot.setOccupiedWith(vehicle);
            return new TicketImpl(spot);
        }
        if (vehicleSize != 1 && vehicleSize <= bigSpotSize) {
            if (!freeBigSpots.isEmpty()) {
                ParkingSpot spot = getFirstAvailableSpot(freeBigSpots);
                spot.setOccupiedWith(vehicle);
                return new TicketImpl(spot);
            }
            Optional<ParkingSpot[]> extendedSpotOptional = getExtendedSpot(vehicleSize);
            if (extendedSpotOptional.isPresent()) {
                ParkingSpot[] spots = extendedSpotOptional.get();
                freeSmallSpots.removeAll(Arrays.asList(spots));
                for (ParkingSpot spot : spots) {
                    spot.setOccupiedWith(vehicle);
                }
                return new TicketImpl(spots);
            } else {
                throw new Exception("Parking is full!");
            }
        }
        throw new Exception("Parking is full!");
    }

    @Override
    public Vehicle takeBack(Ticket ticket) throws Exception {
        if (!isValid(ticket)) {
            throw new Exception("Bad ticket");
        }
        ParkingSpot[] spots = ticket.getSpots();
        Vehicle result = spots[0].remove();
        for (ParkingSpot spot : spots) {
            if (spot.getSize() == 1) {
                freeSmallSpots.add(spot);
            } else {
                freeBigSpots.add(spot);
            }
            spot.remove();
        }
        return result;
    }

    private boolean isValid(Ticket ticket) {
        return allSpots.containsAll(Arrays.asList(ticket.getSpots()));
    }

    @Override
    public String getSpotInfo() {
        return String.format("Parking lot has %d big and %d regular spots (1 big spot = %d regular spots)",
                freeBigSpots.size(), freeSmallSpots.size(), bigSpotSize);
    }

    private ParkingSpot getFirstAvailableSpot(Set<ParkingSpot> spots) {
        ParkingSpot spot = spots.iterator().next();
        spots.remove(spot);
        return spot;
    }
}
