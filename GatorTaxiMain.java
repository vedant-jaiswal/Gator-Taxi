public class GatorTaxiMain {
    private GatorMinHeap rideHeap;
    private GatorRedBlackTree rideTree;


    /*
     * Constructor for Gatortaxi
     */
    GatorTaxiMain() {
        rideHeap = new GatorMinHeap(100);
        rideTree = new GatorRedBlackTree();
    }


    /*
     * Function to print ride
     */
    String Print(int rideNumber) {
        GatorTreeNode rideNode = rideTree.searchRide(rideNumber);
        if (rideNode != rideTree.getTNull()) {
            return rideNode.ride.printRide();
        }
            
        return "(0,0,0)";
    }


    /*
     * Function to print all rides in given interval
     */
    String Print(int rideNumber1, int rideNumber2) {
        GatorRide[] rides = rideTree.getRidesInInterval(rideNumber1, rideNumber2);
        String result = "";

        for (int i=0; i<rides.length-1; i++) {
            result += (
                String.format(
                    "(%d,%d,%d),", 
                    rides[i].rideNumber, rides[i].rideCost, rides[i].tripDuration
                )
            );
        }

        int i = rides.length-1;
        if (i > -1){
            result += (
                String.format(
                    "(%d,%d,%d)", 
                    rides[i].rideNumber, rides[i].rideCost, rides[i].tripDuration
                )
            );
        }
        
        return result;
    }


    /*
     * Function to insert a ride
     */
    public boolean Insert(int rideNumber, int rideCost, int tripDuration) {
        // search if already exists
        GatorTreeNode node = rideTree.searchRide(rideNumber);
        if (node != rideTree.getTNull())
            return false;

        // create GatorRide
        GatorRide newRide = new GatorRide(rideNumber, rideCost, tripDuration);
        // create GatorTreeNode
        GatorTreeNode newRideNode = new GatorTreeNode(newRide);

        // add ride to heap
        rideHeap.insert(newRideNode);
        // add node to RB tree
        rideTree.insert(newRideNode);

        return true;
    }


    /*
     * Function to get next ride
     */
    public String GetNextRide() {
        if (rideHeap.isEmpty()) {
            return "No active ride requests";
        } else {
            // get the next ride's number
            int nextRideNumber = rideHeap.peekNextRideNumber();
            // delete it from RB Tree
            rideTree.deleteNode(nextRideNumber);
            // remove from heap as well
            GatorTreeNode nextRide = rideHeap.remove();

            return nextRide.ride.printRide();
        }
    }


    /*
     * Function to cancel ride
     */
    public void CancelRide(int rideNumber) {
        // get the node which is altered
        int indexToDelete = rideTree.deleteNode(rideNumber);

        // if such node exists
        if (indexToDelete != -1) {
            // remove the node
            rideHeap.removeNode(indexToDelete);
        }
    }


    /*
     * Function to update trip
     */
    void UpdateTrip(int rideNumber, int new_tripDuration) {
        // find the ride
        GatorTreeNode rideNode = rideTree.searchRide(rideNumber);

        if (rideNode == rideTree.getTNull())
            return;
        
        // if new duration < current duration, update
        if (new_tripDuration < rideNode.ride.tripDuration) {
            rideNode.ride.tripDuration = new_tripDuration;
            rideHeap.minHeapify(rideNode.indexInHeap);
        }

        // if current TD < new TD <= 2 * current TD
        // cancel ride, make new request with cost+10
        else if (new_tripDuration > rideNode.ride.tripDuration && new_tripDuration <= (2 * rideNode.ride.tripDuration)) {
            int curRideCost = rideNode.ride.rideCost;
            CancelRide(rideNumber);
            Insert(rideNumber, curRideCost + 10, new_tripDuration);
        }

        // if new TD greater than twice the current TD, cancel the ride
        else if (new_tripDuration > (2 * rideNode.ride.tripDuration)) {
            CancelRide(rideNumber);
        }
    }
}
