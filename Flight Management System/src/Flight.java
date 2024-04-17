import  java.util.Date;

public class Flight {
    private String flightNumber;
    private Date date;
    private String source;
    private String destination;
    private Passenger[] passengers;
    private int maxPassenger;
    private int currentCount;

    // Constructor to initialize flight details
    public Flight(String flightNumber, Date date, String source, String destination, int maxPassenger) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.maxPassenger = maxPassenger;
        this.passengers = new Passenger[maxPassenger];
        this.currentCount = 0;

    }

    // Getter for maximum passenger capacity
    public int getMaxPassenger() {
        return maxPassenger;
    }

    // Method to add a passenger to the flight
    public void addPassenger(Passenger passenger) {
        if (currentCount < maxPassenger) {
            passengers[currentCount++] = passenger;
        } else {
            System.out.println(" The flight is full. Cannot add more passengers.");
        }
    }

    // Method to book a flight for a passenger by Passport ID
    public void bookFlight(int passportId) {
        for (int i = 0; i < currentCount; i++) {

            // Check if the Passport ID matches the one provided
            if (passengers[i].getPassportId() == passportId) {
                passengers[i].bookFlight();
                System.out.println("Flight booked for passenger with Passport ID: " + passportId);
                return; // Exit the method after booking flight
            }
        }
        // If no passenger with the provided Passport ID is found
        System.out.println("Passenger with Passport ID: " + passportId+ " not found. ");
    }

    // Method to cancel a flight for a passenger by Passport ID
    public void cancelFlight(int passportId) {
        for (int i = 0; i < currentCount; i++) {

            if (passengers[i].getPassportId() == passportId) {
                 passengers[i].cancelFlight();
                 System.out.println("Flight canceled for passenger with Passport ID: " + passportId);
                 return; // Exit method after canceling the flight
            }
        }
        System.out.println("Passenger with Passport ID: " + passportId + " not found.");
    }

    // Method to display information for all passengers on the flight
    public void displayAllPassengers() {
        System.out.println("Passengers on Flight" + flightNumber);
        for (int i = 0; i < currentCount; i++) {
            System.out.println(passengers[i].toString());
        }
    }

    // Override of the toString method to provide a custom string representation of a Flight object
    @Override
    public String toString() {

        // Construct and return a string containing Flight details, including flight number, date, source and destination
        return "Flight Number: " + flightNumber + ", Date: " + date + ", Source: " + source + ", Destination " + destination;
    }
}
