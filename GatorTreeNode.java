/*
 * Node to store all the information related to the ride
 * indexToHeap stores the index of the node in the heap
 */
public class GatorTreeNode {
    GatorRide ride;
    int indexInHeap;
    int color;
    GatorTreeNode left;
    GatorTreeNode right;
    GatorTreeNode parent;

    GatorTreeNode(GatorRide ride) {
        this.ride = ride;
        this.indexInHeap = -1;
        // 1 -> RED, 0-> BLACK
        this.color = 1;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}