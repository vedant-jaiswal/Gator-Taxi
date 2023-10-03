import java.util.List;
import java.util.ArrayList;


/*
 * Implementation of Red-Black Tree with pointers
 */
public class GatorRedBlackTree {
  private GatorTreeNode root;
  private GatorTreeNode TNULL;

  public GatorRedBlackTree() {
    TNULL = new GatorTreeNode(null);
    TNULL.color = 0;
    TNULL.left = null;
    TNULL.right = null;
    root = TNULL;
  }


  /*
   * Function to fetch TNULL object
   * for comparison
   */
  public GatorTreeNode getTNull() {
      return this.TNULL;
  }
  

  /*
   * Function to find nodes in the given interval
   */
  public GatorRide[] getRidesInInterval(int key1, int key2) {
    List<GatorRide> rides = new ArrayList<GatorRide>();
    if (root == TNULL) {
        return rides.toArray(new GatorRide[rides.size()]);
    }

    // Perform an inorder traversal of the BST
    visitInorder(root, key1, key2, rides);
    
    return rides.toArray(new GatorRide[rides.size()]);
  }
  

  /*
   * Function to insert a ride node
   */
	public void insert(GatorTreeNode rideNode) {
		// Ordinary Binary Search Insertion
		rideNode.parent = null;
		rideNode.left = TNULL;
		rideNode.right = TNULL;
		rideNode.color = 1; // new rideNode must be red

		GatorTreeNode newParent = null;
		GatorTreeNode tmpNode = this.root;

		while (tmpNode != TNULL) {
			newParent = tmpNode;
			if (rideNode.ride.rideNumber < tmpNode.ride.rideNumber) {
				tmpNode = tmpNode.left;
			} else {
				tmpNode = tmpNode.right;
			}
		}

		// newParent is parent of tmpNode
		rideNode.parent = newParent;
		if (newParent == null) {
			root = rideNode;
		} else if (rideNode.ride.rideNumber < newParent.ride.rideNumber) {
			newParent.left = rideNode;
		} else {
			newParent.right = rideNode;
		}

		// if new rideNode is a root node, simply return
		if (rideNode.parent == null){
			rideNode.color = 0;
			return;
		}

		// if the grandparent is null, simply return
		if (rideNode.parent.parent == null) {
			return;
		}

		// Fix the tree
		fixInsert(rideNode);
	}


	/*
   * Function to delete a ride node
   */
	public int  deleteNode(int data) {
		return deleteNodeHelper(this.root, data);
	}


  /*
   * Function to search a node in RB Tree
   */
  public GatorTreeNode searchRide(int rideNumber) {
    GatorTreeNode node = root;
    while (node != TNULL) {
      if (rideNumber == node.ride.rideNumber) {
        return node;
      } else if (rideNumber < node.ride.rideNumber) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
  
    return TNULL;
  }


  // ----------------------------------------------------------
  // --------------- Helper Functions Below -------------------
  // ----------------------------------------------------------


  /*
   * Helper function for getRidesInInterval
   */
  private void visitInorder(GatorTreeNode node, int min, int max, List<GatorRide> rideList) {
    if (node == TNULL) {
        return;
    }

    // Recursively visit left subtree if node's value is greater than min
    if (node.ride.rideNumber > min) {
        visitInorder(node.left, min, max, rideList);
    }

    // Add node's value to the rideList if it falls within the given interval
    if (node.ride.rideNumber >= min && node.ride.rideNumber <= max) {
        rideList.add(node.ride);
    }

    // Recursively visit right subtree if node's value is less than max
    if (node.ride.rideNumber < max) {
        visitInorder(node.right, min, max, rideList);
    }
  }
  

	/*
   * Helper function to fix RB Tree 
   * after deletion of a rideNode
   */
	private void fixDelete(GatorTreeNode nodeToFix) {
		GatorTreeNode sibling;
		while (nodeToFix != root && nodeToFix.color == 0) {
			if (nodeToFix == nodeToFix.parent.left) {
				sibling = nodeToFix.parent.right;
				if (sibling.color == 1) {
					// sibling is red
					sibling.color = 0;
					nodeToFix.parent.color = 1;
					leftRotate(nodeToFix.parent);
					sibling = nodeToFix.parent.right;
				}

				if (sibling.left.color == 0 && sibling.right.color == 0) {
					// sibling and its children are all black
					sibling.color = 1;
					nodeToFix = nodeToFix.parent;
				} else {
					if (sibling.right.color == 0) {
						// sibling black, leftChild red, rightChild black
						sibling.left.color = 0;
						sibling.color = 1;
						rightRotate(sibling);
						sibling = nodeToFix.parent.right;
					} 

					// sibling black, rightChild red
					sibling.color = nodeToFix.parent.color;
					nodeToFix.parent.color = 0;
					sibling.right.color = 0;
					leftRotate(nodeToFix.parent);
					nodeToFix = root;
				}
			} else {
				sibling = nodeToFix.parent.left;
				if (sibling.color == 1) {
					// sibling is red
					sibling.color = 0;
					nodeToFix.parent.color = 1;
					rightRotate(nodeToFix.parent);
					sibling = nodeToFix.parent.left;
				}

				if (sibling.right.color == 0 && sibling.right.color == 0) {
					// sibling and its children are all black
					sibling.color = 1;
					nodeToFix = nodeToFix.parent;
				} else {
					if (sibling.left.color == 0) {
						// sibling black, leftChild red, rightChild black
						sibling.right.color = 0;
						sibling.color = 1;
						leftRotate(sibling);
						sibling = nodeToFix.parent.left;
					} 

					// sibling black, rightChild red
					sibling.color = nodeToFix.parent.color;
					nodeToFix.parent.color = 0;
					sibling.left.color = 0;
					rightRotate(nodeToFix.parent);
					nodeToFix = root;
				}
			} 
		}
		nodeToFix.color = 0;
	}


  /*
   * Helper function to put/transplant 
   * one node in place of another
   */
	private void replace(GatorTreeNode oldNode, GatorTreeNode newNode){
		if (oldNode.parent == null) {
			root = newNode;
		} else if (oldNode == oldNode.parent.left){
			oldNode.parent.left = newNode;
		} else {
			oldNode.parent.right = newNode;
		}
		newNode.parent = oldNode.parent;
	}


  /*
   * Helper function to given ride
   * in subtree of the given node
   * and perform delete operations
   */
	private int deleteNodeHelper(GatorTreeNode node, int rideNumber) {
		// find the node containing rideNumber
		GatorTreeNode targetRideNode = TNULL;
		GatorTreeNode tmpChildNode, tmpNode;
		while (node != TNULL){
			if (node.ride.rideNumber == rideNumber) {
				targetRideNode = node;
			}

			if (node.ride.rideNumber <= rideNumber) {
				node = node.right;
			} else {
				node = node.left;
			}
		}

		if (targetRideNode == TNULL) {
			return -1;
		} 

    int index = targetRideNode.indexInHeap;

		tmpNode = targetRideNode;
		int tmpNodeOriginalColor = tmpNode.color;
		if (targetRideNode.left == TNULL) {
			tmpChildNode = targetRideNode.right;
			replace(targetRideNode, targetRideNode.right);
		} else if (targetRideNode.right == TNULL) {
			tmpChildNode = targetRideNode.left;
			replace(targetRideNode, targetRideNode.left);
		} else {
			tmpNode = inorderSuccessorOf(targetRideNode.right);
			tmpNodeOriginalColor = tmpNode.color;
			tmpChildNode = tmpNode.right;
			if (tmpNode.parent == targetRideNode) {
				tmpChildNode.parent = tmpNode;
			} else {
				replace(tmpNode, tmpNode.right);
				tmpNode.right = targetRideNode.right;
				tmpNode.right.parent = tmpNode;
			}

			replace(targetRideNode, tmpNode);
			tmpNode.left = targetRideNode.left;
			tmpNode.left.parent = tmpNode;
			tmpNode.color = targetRideNode.color;
		}
		if (tmpNodeOriginalColor == 0){
			fixDelete(tmpChildNode);
		}

    return index;
	}
	

	/*
   * Helper function to fix tree
   * after insert operation
   */
	private void fixInsert(GatorTreeNode rideNode){
		GatorTreeNode uncle;
		while (rideNode.parent.color == 1) {
			if (rideNode.parent == rideNode.parent.parent.right) {
				uncle = rideNode.parent.parent.left;
				if (uncle.color == 1) {
					// parent red, uncle red
					uncle.color = 0;
					rideNode.parent.color = 0;
					rideNode.parent.parent.color = 1;
					rideNode = rideNode.parent.parent;
				} else {
					if (rideNode == rideNode.parent.left) {
						// parent is right child, node is left child
						rideNode = rideNode.parent;
						rightRotate(rideNode);
					}
					// parent is right child, node is right child
					rideNode.parent.color = 0;
					rideNode.parent.parent.color = 1;
					leftRotate(rideNode.parent.parent);
				}
			} else {
				uncle = rideNode.parent.parent.right;

				if (uncle.color == 1) {
					// parent is black, uncle is black
					uncle.color = 0;
					rideNode.parent.color = 0;
					rideNode.parent.parent.color = 1;
					rideNode = rideNode.parent.parent;	
				} else {
					if (rideNode == rideNode.parent.right) {
						// parent is left child, node is right child
						rideNode = rideNode.parent;
						leftRotate(rideNode);
					}
					// parent is left child, node is left child
					rideNode.parent.color = 0;
					rideNode.parent.parent.color = 1;
					rightRotate(rideNode.parent.parent);
				}
			}
			if (rideNode == root) {
				break;
			}
		}
		root.color = 0;
	}


	/*
   * Helper function to find the inorder
   * successor of a node
   */
	private GatorTreeNode inorderSuccessorOf(GatorTreeNode node) {
		while (node.left != TNULL) {
			node = node.left;
		}
		return node;
	}


	/*
   * Helper function to perform
   * left rotation
   */
	private void leftRotate(GatorTreeNode rideNode) {
		GatorTreeNode rightChild = rideNode.right;
		rideNode.right = rightChild.left;
		if (rightChild.left != TNULL) {
			rightChild.left.parent = rideNode;
		}
		rightChild.parent = rideNode.parent;
		if (rideNode.parent == null) {
			this.root = rightChild;
		} else if (rideNode == rideNode.parent.left) {
			rideNode.parent.left = rightChild;
		} else {
			rideNode.parent.right = rightChild;
		}
		rightChild.left = rideNode;
		rideNode.parent = rightChild;
	}

	
  /*
   * Helper function to perform
   * right rotation
   */
	private void rightRotate(GatorTreeNode rideNode) {
		GatorTreeNode leftChild = rideNode.left;
		rideNode.left = leftChild.right;
		if (leftChild.right != TNULL) {
			leftChild.right.parent = rideNode;
		}
		leftChild.parent = rideNode.parent;
		if (rideNode.parent == null) {
			this.root = leftChild;
		} else if (rideNode == rideNode.parent.right) {
			rideNode.parent.right = leftChild;
		} else {
			rideNode.parent.left = leftChild;
		}
		leftChild.right = rideNode;
		rideNode.parent = leftChild;
	}

}