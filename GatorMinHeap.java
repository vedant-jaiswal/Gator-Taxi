/*
 * Array implementation of heap of rides
 */
public class GatorMinHeap {
    GatorTreeNode[] heap;
    int size;
    

    /*
     * Constructor initializing heap with given max size
     */
    GatorMinHeap(int maxSize) {
        // initiating min heap with max size
        heap = new GatorTreeNode[maxSize];
        // current size of the min heap
        size = 0;
    }


    /*
     * Utility function to swap elements
     */
    void swap(int i, int j) {
        // update heap indices of the nodes
        heap[i].indexInHeap = j;
        heap[j].indexInHeap = i;
        
        // swap nodes
        GatorTreeNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    

    /*
     * Helper functions to get parent and children
     */
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return (2 * index) + 1;
    }

    private int rightChild(int index) {
        return (2 * index) + 2;
    }


    /*
     * Function to heapify
     */
    public void minHeapify(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && heap[left].ride.rideCost < heap[smallest].ride.rideCost) {
            smallest = left;
        } else if (left < size && heap[left].ride.rideCost == heap[smallest].ride.rideCost &&
                heap[left].ride.tripDuration < heap[smallest].ride.tripDuration) {
            smallest = left;
        }

        if (right < size && heap[right].ride.rideCost < heap[smallest].ride.rideCost) {
            smallest = right;
        } else if (right < size && heap[right].ride.rideCost == heap[smallest].ride.rideCost &&
                heap[right].ride.tripDuration < heap[smallest].ride.tripDuration) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            minHeapify(smallest);
        }
    }


    /*
     * Function to insert element
     */
    public void insert(GatorTreeNode rideNode) {
        // if heap is full, do nothing
        if (size >= heap.length) {
            return;
        }

        int index = size;
        heap[index] = rideNode;
        rideNode.indexInHeap = index;
        size += 1;

        while (index > 0) {
            if (heap[parent(index)].ride.rideCost > heap[index].ride.rideCost) {
                swap(parent(index), index);
            } else if (heap[parent(index)].ride.rideCost == heap[index].ride.rideCost && heap[parent(index)].ride.tripDuration > heap[index].ride.tripDuration) {
                swap(parent(index), index);
            }
            index = parent(index);
        }
    }


    /*
     * Function to remove minimum element or the Root
     */
    public GatorTreeNode remove() {
        if (size <= 0) {
            return null;
        }

        // exchange root with last element
        GatorTreeNode min = heap[0];
        heap[0] = heap[size - 1];
        heap[0].indexInHeap = 0;

        // update size and heapify
        heap[size-1] = null;
        size--;
        minHeapify(0);

        return min;
    }


    /*
     * Function to remove a node
     */
    void removeNode(int indexToRemove) {
        // Check if index is valid
        if (indexToRemove < 0 || indexToRemove >= size) {
            System.out.println("Ride number not found in heap");
            return;
        }

        // exhange node with the last element
        heap[indexToRemove] = heap[size - 1];
        heap[indexToRemove].indexInHeap = indexToRemove;

        // update size and heapify
        heap[size-1] = null;
        size--;
        minHeapify(indexToRemove);
    }
    

    /*
     * Function to check the ride number
     * of the trip at the root of the heap
     */
    int peekNextRideNumber() {
        return heap[0].ride.rideNumber;
    }
    

    /*
     * Function to check if the min heap is empty
     */
    boolean isEmpty() {
        return size == 0;
    }
}
