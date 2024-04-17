import java.awt.*;
import java.util.Date;
import java.util.Scanner;
import  java.util.ArrayList;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // Create a Flight
        Flight flight = new Flight("F123", new Date(), "SourceCity", "DestinationCity", 5);

        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        //Create an ArrayList to store passenger objects
        ArrayList<Passenger> passengers = new ArrayList<>();

        // Start an infinite loop for the main menu
        while (true) {
            System.out.println("Welcome to the Airlines Management System");
            System.out.println("1. Add Passenger");
            System.out.println("2. Book FLight");
            System.out.println("3. Cancel Flight");
            System.out.println("4. Display All Passengers");
            System.out.println("5. Display Passengers in Last 7 Days");
            System.out.println("6. Exit");

            System.out.println("Enter your choice: ");

            // Read the user's choice from the input
            int choice = scanner.nextInt();
            scanner.nextLine(); // resume to next line character

            switch (choice) {
                case 1:
                    // Initialize variables to store passenger information
                    int passportId;
                    String firstName;
                    String lastName;
                    String age;
                    double flightFare = 0.0;
                    Date purchaseDate = new Date();

                    // Check if maximum capacity is reached
                    if(passengers.size() >= flight.getMaxPassenger()) {
                        System.out.println("Maximum passenger capacity reached. Cannot add more passengers.");
                        break; // Exit passenger addition process
                    }

                    // Collect Passport ID
                    do {
                        System.out.print("Enter Passport ID (6 digits): ");
                        passportId = scanner.nextInt();
                        scanner.nextLine(); // newline character
                    } while (String.valueOf(passportId).length() != 6);

                    // Collect First Name (letters only)
                    do {
                        System.out.print("Enter First Name (letters only): ");
                        firstName = scanner.nextLine();
                    } while (!firstName.matches("^[a-zA-Z]+$"));

                    // Collect Last Name (letters only)
                    do {
                        System.out.print("Enter Last Name (letters only): ");
                        lastName = scanner.nextLine();
                    } while (!lastName.matches("^[a-zA-Z]+$"));

                    // Collect Age (numbers only)
                    do {
                        System.out.print("Enter Age (numbers only): ");
                        age = scanner.nextLine();
                    } while (!age.matches("^[0-9]+$"));

                    // Collect Flight Fare
                    do {
                        System.out.print("Enter Flight Fare: ");
                        try {
                            flightFare = Double.parseDouble(scanner.nextLine());

                        // Check if the entered flight fare is a positive value
                        if (flightFare <= 0) {
                            System.out.println("Flight must be a positive value.");
                          }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid numeric value for the flight fare.");
                            flightFare = -1; // Set to -1 to trigger re-entry
                        }
                    } while (flightFare <= 0);

                    // Create a Passenger object with the current purchase date
                   Passenger passenger = new Passenger(passportId, firstName, lastName, age, flightFare, purchaseDate);

                   // Display passenger information for confirmation
                    System.out.println("Passenger Information:");
                    passenger.displayInfo();

                    // Ask the user to confirm the entered information
                    System.out.print("Is this information correct? (1: Yes, 2: No): ");
                    int confirm = scanner.nextInt();
                    scanner.nextLine();

                    // If the user choose to correct any information (confirm ==2)
                    while (confirm == 2) {
                        System.out.println("Please correct the incorrect information:");
                        System.out.print("Which information should be corrected? (a: Passport ID, b: First Name, c: Last Name, d: Age, e: Flight Fare): ");
                        String incorrectChoice = scanner.nextLine();

                        // Check which information should be corrected
                        switch (incorrectChoice) {
                            case "a":
                                // Re-enter Passport ID
                                do {
                                    System.out.print("Enter Passport ID (6 digits): ");
                                    passportId = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character
                                } while (String.valueOf(passportId).length() != 6);
                                break;
                            case "b":
                                // Re-enter First Name
                                do {
                                    System.out.print("Enter First Name (letters only): ");
                                    firstName = scanner.nextLine();
                                } while (!firstName.matches("^[a-zA-Z]+$"));
                                break;
                            case "c":
                                // Re-enter Last Name
                                do {
                                    System.out.print("Enter Last Name (letters only): ");
                                    lastName = scanner.nextLine();
                                } while (!lastName.matches("^[a-zA-Z]+$"));
                                break;
                            case "d":
                                // Re-enter Age
                                do {
                                    System.out.print("Enter Age (numbers only): ");
                                    age = scanner.nextLine();
                                } while (!age.matches("^[0-9]+$"));
                                break;
                            case "e":
                                // Re-enter Flight Fare
                                do {
                                    System.out.print("Enter Flight Fare: ");
                                    try {
                                        flightFare = Double.parseDouble(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        flightFare = -1; // Set to trigger re-entry
                                    }
                                } while (flightFare <= 0);
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;
                        }

                        // Display corrected information
                        System.out.println("Passenger Information:");
                        System.out.println("Passport ID: " + passportId);
                        System.out.println("First Name: " + firstName);
                        System.out.println("Last Name: " + lastName);
                        System.out.println("Age: " + age);
                        System.out.println("Flight Fare: " + flightFare);

                        // Ask user to confirm the corrected information
                        System.out.print("Is this information correct now? (1: Yes, 2: No): ");
                        confirm = scanner.nextInt();
                        scanner.nextLine();
                    }

                    if (confirm == 1) {
                        passengers.add(passenger);
                        System.out.println("Passenger was successfully added");
                        break; // Exit loop for adding passenger and return to the menu
                    }
                    break;

                case 2:
                    // Book Flight
                    System.out.println("Enter Passport ID to book flight: ");
                    passportId = scanner.nextInt();
                    flight.bookFlight(passportId);
                    break;
                case 3:
                    // Cancel Flight
                    System.out.println("Enter Passport ID to cancel flight: ");
                    passportId = scanner.nextInt();
                    flight.cancelFlight(passportId);
                    break;
                case 4:
                    // Display All passengers
                    flight.displayAllPassengers();
                    break;
                case 5:
                    // Display Passengers Who Bought Tickets in the Last 7 Days
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());

                    // Subtract 7 days from the current date
                    calendar.add(Calendar.DAY_OF_YEAR, -7);
                    Date sevenDaysAgo = calendar.getTime();

                    System.out.println("Passengers who bought tickets in the last 7 days:");

                    // Iterate through the list of passengers
                    for (Passenger p : passengers) {
                        // Check if the passenger's purchase date is after the calculated date
                        if (p.getPurchaseDate().after(sevenDaysAgo)) {
                            // Display information for passengers who meet the criteria
                            p.displayInfo();
                        }
                    }
                    break;
                case 6:
                    // Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;

            }
        }

    }
}

