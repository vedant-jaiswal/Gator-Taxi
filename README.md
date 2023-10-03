Certainly! Here's a README file for the GatorTaxi project:

# GatorTaxi - Taxi Service Management System

Author: Vedant Jaiswal
UFID: 3882-7983
Email: vedantjaiswal@ufl.edu

## Introduction

The GatorTaxi project aims to create a taxi service management system that utilizes a combination of data structures, including a min heap and a red-black tree. This system can perform various functions such as inserting ride requests, canceling rides, updating ride information, and printing ride details.

## How to Run

To run the GatorTaxi program, follow these instructions:

1. Download the project files from the provided ZIP archive (`Jaiswal_Vedant.zip`).

2. Unzip the downloaded file:
   ```
   unzip Jaiswal_Vedant.zip
   ```

3. Compile the program:
   ```
   make
   ```

4. Run the program with an input file:
   ```
   java gatorTaxi <input filename>
   ```

5. To view the contents of the output file, use the following command:
   ```
   cat output_file.txt
   ```

## Components

A ride in the GatorTaxi service is defined by a triplet with the following attributes:
- `rideNumber`: A unique identifier for the ride.
- `rideCost`: The cost of the ride.
- `tripDuration`: The total time it will take to complete the ride.

The program offers the following functions:

1. `Print(int rideNumber)`: Prints the details of a ride with the given ride number.

2. `Print(int rideNumber1, int rideNumber2)`: Prints details of rides within the specified range of ride numbers.

3. `Insert(rideNumber, rideCost, tripDuration)`: Adds a new ride request to the system.

4. `GetNextRide()`: Returns and removes the next ride to process, prioritizing by ride cost and trip duration.

5. `Cancel(rideNumber)`: Deletes a ride request from the system.

6. `Update(rideNumber, tripDuration)`: Updates a ride based on specified rules, including modifying trip duration and cost or canceling the ride.

## Implementation Details

The GatorTaxi system is implemented using custom data structures and classes:

### Custom Data Structures

1. `GatorRide`: Represents a ride request with attributes `rideNumber`, `rideCost`, and `tripDuration`.

2. `GatorTreeNode`: Represents a node in the red-black tree, which also serves as a node in the min heap. This structure maintains pointers between the min heap and the red-black tree.

3. `GatorMinHeap`: Implements a min heap to prioritize ride requests based on cost and duration.

4. `GatorRedBlackTree`: Represents a red-black tree for managing ride requests by ride number.

5. `GatorTaxiMain`: Main class for the GatorTaxi system, which combines and manages the above data structures.

### Functions

- `gatorTaxi`: The main entry point of the program that reads input, performs operations, and writes output.

- `GatorTaxiMain`: Implements functions for printing, inserting, getting next rides, canceling rides, and updating rides.

- `GatorMinHeap`: Functions to manage the min heap, including insertion, removal, and min-heap maintenance.

- `GatorRedBlackTree`: Functions for inserting, deleting, searching, and maintaining the red-black tree structure.

## Time and Space Complexity

Each function in the system specifies its time and space complexity, with explanations for the calculations.

Please refer to the provided code for detailed implementation details.

## Note

This README provides an overview of the GatorTaxi project. For a more detailed understanding of the code and its workings, refer to the source code files.

For any questions or issues, please contact the author, Vedant Jaiswal, at vedantjaiswal@ufl.edu.