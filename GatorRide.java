public class GatorRide {
    int rideNumber;
    int rideCost;
    int tripDuration;
    
    GatorRide(int rideNumber, int rideCost, int tripDuration) {
        this.rideNumber = rideNumber;
        this.rideCost = rideCost;
        this.tripDuration = tripDuration;
    }

    public String printRide() {
        return (String.format("(%d,%d,%d)", this.rideNumber, this.rideCost, this.tripDuration));
    }
}
