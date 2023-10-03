import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class gatorTaxi {
    public static void main (String args[]) {
        GatorTaxiMain taxi = new GatorTaxiMain();

        // File name to be read
        String fileName = args[0];

        // Read file and execute corresponding functions
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Define output writer
            BufferedWriter writer = new BufferedWriter(new FileWriter("output_file.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                // Split based on '('
                String[] tokens = line.split("\\(");
                // Get function name
                String command = tokens[0].trim();
                // Get parameters for the function
                String[] arguments = tokens[1].substring(0, tokens[1].length() - 1).split(",");
                switch (command) {
                    case "Insert":
                        int rideNumber = Integer.parseInt(arguments[0].trim());
                        int rideCost = Integer.parseInt(arguments[1].trim());
                        int tripDuration = Integer.parseInt(arguments[2].trim());
                        if (! taxi.Insert(rideNumber, rideCost, tripDuration) ) {
                            writer.write( "Duplicate RideNumber" );
                            writer.close();
                            System.exit(0);
                        }
                        break;
                    case "GetNextRide":
                        writer.write( taxi.GetNextRide() );
                        writer.newLine();
                        break;
                    case "Print":
                        int rideNumber1 = Integer.parseInt(arguments[0].trim());
                        if (arguments.length == 1) {
                            String tmpString = taxi.Print(rideNumber1);
                            if (tmpString != "") {
                                writer.write( tmpString );
                                writer.newLine();
                            }
                        } else {
                            int rideNumber2 = Integer.parseInt(arguments[1].trim());
                            String tmpString = taxi.Print(rideNumber1, rideNumber2);
                            if (tmpString != "") {
                                writer.write( tmpString );
                            } else {
                                writer.write( "(0,0,0)" );
                            }
                            writer.newLine();
                        }
                        break;
                    case "UpdateTrip":
                        int rideNumberToUpdate = Integer.parseInt(arguments[0].trim());
                        int newTripDuration = Integer.parseInt(arguments[1].trim());
                        taxi.UpdateTrip(rideNumberToUpdate, newTripDuration);
                        break;
                    case "CancelRide":
                        int rideNumberToCancel = Integer.parseInt(arguments[0].trim());
                        taxi.CancelRide(rideNumberToCancel);
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }
            writer.close();

        } catch (IOException e) {
            System.out.println( e.getMessage() );
        }
    }
}