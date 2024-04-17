import java.util.Date;
public class Passenger {
    private int passportId;
    private String firstName;
    private String lastName;
    private String age;
    private double flightFare;
    private boolean hasBooked;
    private Date purchaseDate;

    // Constructor for creating a Passenger object
    public Passenger(int passportId, String firstName, String lastName, String age, double flightFare, Date purchaseDate) {
        this.passportId = passportId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.flightFare = flightFare;
        this.hasBooked = false;
        this.purchaseDate = purchaseDate;
    }

    // Getter for retrieving the passport ID
    public int getPassportId() {
        return passportId;
    }

    // Getter for retrieving the purchase date
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    // Method to book a flight for the passenger
    public void bookFlight() {
        hasBooked = true;
    }

    // Method to cancel a booked flight for the passenger
    public void cancelFlight() {
        hasBooked = false;
    }

    // Method to display passenger information
    public void displayInfo() {
        System.out.println("Passport ID: " + passportId);
        System.out.println("Name: " +firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Flight Fare: " + flightFare);
        System.out.println("Booking Status: " + (hasBooked ? "Booked" : "Not Booked"));
    }

    // Override of the toString method to provide a custom string representation of a Passenger object
    @Override
    public String toString() {
        return "Passport ID: " + passportId + ", Name: " + firstName + " " + lastName;
    }
}

// Subclass of Passenger, representing Frequent Flyer Passengers
class FrequentFlyerPassenger extends Passenger {
    private String frequentFlyerNumber;
    private int collectedMiles;

    // Constructor for creating a FrequentFlyerPassenger object
    public FrequentFlyerPassenger(int passportId, String firstName, String lastName, String age, double flightFare, Date purchaseDate, String frequentFlyerNumber) {
        super(passportId, firstName, lastName, age, flightFare, purchaseDate);
        this.frequentFlyerNumber = frequentFlyerNumber;
        this.collectedMiles = 0;
    }

    // Getter for retrieving the frequent flyer number
    public String getFrequentFlyerNumber() {
        return frequentFlyerNumber;
    }

    // Method to add miles to the collected miles of the frequent flyer
    public void addMiles(int miles) {
        collectedMiles += miles;
    }

    // Override of the displayInfo method to include frequent flyer information
    @Override
    public void displayInfo() {
        super.displayInfo(); // Calling of the parent class displayInfo method
        System.out.println("Frequent Flyer Number: " + frequentFlyerNumber);
        System.out.println("Collected Miles: " + collectedMiles);
    }
}
