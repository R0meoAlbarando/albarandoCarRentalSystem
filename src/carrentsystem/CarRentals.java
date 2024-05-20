
package carrentsystem;

import java.util.ArrayList;
import java.util.Scanner;

class CarRent {
    String type;
    String brand;
    String model;
    int year;
    double dailyRate;
    boolean available;
    String plateNumber;

    public CarRent(String type, String brand, String model, int year, double dailyRate, String plateNumber) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.available = true;
        this.plateNumber = plateNumber;
       
    }

    @Override
    public String toString() {
        return brand + " " + model + " (" + type + ") - $" + dailyRate + " per day - Plate Number: " + plateNumber ;
    }
    
    public void updateDailyRate(double newDailyRate) {
        this.dailyRate = newDailyRate;
    }
}

class Booking {
    CarRent car;
    String customerFirstName;
    String customerLastName;
    String contactNumber;
    String rentDate;
    String returnDate;
    String carStatus;

    public Booking(CarRent car, String customerFirstName, String customerLastName, String contactNumber, String carStatus, String rentDate, String returnDate) {
        this.car = car;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.contactNumber = contactNumber;
        this.carStatus = carStatus;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        
    }
    
    public void setStatus(String status) {
        this.carStatus = status;
    }
    
    public double calculateTotalCost() {
        Scanner scanner = new Scanner(System.in);

        double dailyRate = car.dailyRate;

        try {
            long rentTimestamp = scannerDateFormat.parse(rentDate).getTime();
            long returnTimestamp = scannerDateFormat.parse(returnDate).getTime();
            
            long rentalDurationMillis = returnTimestamp - rentTimestamp;
            long rentalDurationDays = rentalDurationMillis / (24 * 60 * 60 * 1000);
            
            double totalCost = rentalDurationDays * dailyRate;

            return totalCost;
        } catch (java.text.ParseException e) {
            System.out.println("Invalid date format. Please use MM/DD/YYYY.");
            return 0.0;
        }
    }

    private static java.text.SimpleDateFormat scannerDateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
}




public class CarRentals{
    ArrayList<CarRent> cars = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void initializeCars() {
    cars.add(new CarRent("Sedan", "Toyota", "Camry", 2023, 50.00, "ABC123"));
    cars.add(new CarRent("SUV", "Honda", "CR-V", 2023, 60.00, "DEF456"));
    cars.add(new CarRent("Compact", "Ford", "Focus", 2023, 45.00, "GHI789"));
    }

    public void displayMenu() {
        System.out.println(" ________________________________");
        System.out.println("||                              ||");
        System.out.println("||    Car Rental System Menu    ||");
        System.out.println("||______________________________||");
        System.out.println("||                              ||");
        System.out.println("||1. Check Vehicle Availability ||");
        System.out.println("||2. Book a Vehicle             ||");
        System.out.println("||3. View Booked Vehicles       ||");
        System.out.println("||4. Process Payment            ||");
        System.out.println("||5. Return Vehicle             ||");
        System.out.println("||6. Add a New Vehicle          ||");
        System.out.println("||7. Update Daily Rate          ||");
        System.out.println("||8. Exit                       ||");
        System.out.println("||______________________________||");
        System.out.print("\nEnter your choice (1-8): ");
        while(!scanner.hasNextInt()){;
            System.out.print("Invalid choice!, Please Enter Again: "); 
        scanner.next();
        }
    }

    public void checkVehicleAvailability() {
        System.out.println("\nAvailable Vehicles:");
        System.out.println("+------------------------------------------------------------------------+");
        System.out.println("|  No. | Brand       | Model       | Type      | Daily Rate  | Plate No. |");
        System.out.println("+------+-------------+-------------+-----------+-------------+-----------+");

        for (int i = 0; i < cars.size(); i++) {
            CarRent car = cars.get(i);
            if (car.available) {
                System.out.printf("|  %-3d | %-11s | %-11s | %-9s | %-11s | %-9s |\n", (i + 1), car.brand, car.model, 
                car.type, car.dailyRate, car.plateNumber);
            }
        }

        System.out.println("+------------------------------------------------------------------------+");
    }
    
    public void bookVehicle() { 
    checkVehicleAvailability();
    System.out.print("\nEnter the number of the vehicle to book: ");
    while (!scanner.hasNextInt()) {
        System.out.print("Invalid choice!, Please Enter Again: "); 
        scanner.next();
    }
    int choice = scanner.nextInt();

    if (choice > 0 && choice <= cars.size()) {
        CarRent selectedCar = cars.get(choice - 1);
        if (selectedCar.available) {
            System.out.print("\nEnter Customer First Name: ");
            String customerFirstName = scanner.next();
            scanner.nextLine();
            System.out.print("Enter Customer Last Name: ");
            String customerLastName = scanner.next();
            scanner.nextLine();
            System.out.print("Enter Contact Number: ");
            String contactNumber = scanner.next();
            System.out.print("Enter rental day (MM/DD/YYYY): ");
            String rentDate = scanner.next();
            System.out.print("Enter return day (MM/DD/YYYY): ");
            String returnDate = scanner.next();
            
            String carStatus = "Booked"; 
            Booking newBooking = new Booking(selectedCar, customerFirstName, customerLastName, contactNumber, carStatus, rentDate, returnDate);
            bookings.add(newBooking);
            selectedCar.available = false;

            System.out.println("\nBooking Successful!");
            } else {
            System.out.println("Sorry, the selected vehicle is not available.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public void viewBookedVehicles() {
        System.out.println("\nBooked Vehicles:");
        System.out.println("+------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-20s | %-25s | %-15s | %-20s | %-20s |\n", "Vehicle", "Customer", "Car Status", "Rent Date", "Return Date");
        System.out.println("|----------------------+---------------------------+-----------------+----------------------+----------------------|");

        for (Booking booking : bookings) {
            System.out.printf("| %-20s | %-25s | %-15s | %-20s | %-20s |\n",
            booking.car.brand + " " + booking.car.model,
            booking.customerFirstName + " " + booking.customerLastName,
            booking.carStatus, booking.rentDate, booking.returnDate);
        }
        System.out.println("+------------------------------------------------------------------------------------------------------------------+");
    }
    
    public void processPayment() {
        System.out.println("Process Payments");
        viewBookedVehicles();
        System.out.print("\nEnter the number of the booking to process payment: ");
        while (!scanner.hasNextInt()) {
        System.out.print("Invalid choice!, Please Enter Again: ");
        scanner.next();
        }
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= bookings.size()) {
            Booking selectedBooking = bookings.get(choice - 1);
        
            if (selectedBooking.carStatus.equals("Paid")) {
                System.out.println("Invalid choice! Vehicle has already been processed.");
            } else {
                double totalCost = selectedBooking.calculateTotalCost();

                System.out.println("+-----------------------------------------------+");
                System.out.println("|            Payment Details                    |");
                System.out.println("|-----------------------------------------------|");
                System.out.printf("| Customer: %-36s|\n", selectedBooking.customerFirstName + " " + selectedBooking.customerLastName);
                System.out.printf("| Vehicle:  %-36s|\n", selectedBooking.car.brand + " " + selectedBooking.car.model);
                System.out.printf("| Date Rented: %-33s|\n", selectedBooking.rentDate);
                System.out.printf("| Date Return: %-33s|\n", selectedBooking.returnDate);
                System.out.printf("| Total Cost: $%-33.2f|\n", totalCost);
                System.out.println("+-----------------------------------------------+");

                System.out.print("Confirm payment (Y/N): ");
                String confirmation = scanner.next().toUpperCase();

                if (confirmation.equals("Y")) {
                    selectedBooking.setStatus("Paid");
                    System.out.println("Payment processed successfully!");
                } else {
                    System.out.println("Payment cancelled. Vehicle availability not updated.");
                }
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
    
    public void returnVehicles(){
        viewBookedVehicles();
        System.out.print("\nEnter the number of Vehicle to Return: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid choice!, Please Enter Again: ");
            scanner.next();
        }
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= bookings.size()) {
            Booking selectedBooking = bookings.get(choice - 1);
            if (selectedBooking.carStatus.equals("Booked")) {
                System.out.println("The vehicle is still unpaid! Make a payment first.");
            } else {
               System.out.println("+-----------------------------------------------+");
               System.out.println("|            Return Details                     |");
               System.out.println("|-----------------------------------------------|");
               System.out.printf("| Customer: %-36s|\n", selectedBooking.customerFirstName + " " + selectedBooking.customerLastName);
               System.out.printf("| Vehicle:  %-36s|\n", selectedBooking.car.brand + " " + selectedBooking.car.model);
               System.out.printf("| Date Rented: %-33s|\n", selectedBooking.rentDate);
               System.out.printf("| Date Return: %-33s|\n", selectedBooking.returnDate);
               System.out.println("+-----------------------------------------------+");

               bookings.remove(selectedBooking);
               selectedBooking.car.available = true;
               System.out.println("Vehicle Return successfully!");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public void addNewVehicle() {
        System.out.println("\nAdding a New Vehicle:");
        System.out.print("Enter Vehicle type: ");
        String type = scanner.next();
        System.out.print("Enter Brand: ");
        String brand = scanner.next();
        System.out.print("Enter Model: ");
        String model = scanner.next();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        System.out.print("Enter Daily Rate: $");
        double dailyRate = scanner.nextDouble();
        System.out.print("Enter Plate Number: ");
        String plateNumber = scanner.next();

        CarRent newCar = new CarRent(type, brand, model, year, dailyRate, plateNumber);
        cars.add(newCar);
        System.out.println("New Vehicle added successfully!");
    }
    
    public void updateDailyRate() {
        checkVehicleAvailability();
        System.out.print("\nEnter the number of the vehicle to update the Daily Rate: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid choice!, Please Enter Again: ");
            scanner.next();
        }
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= cars.size()) {
            CarRent selectedCar = cars.get(choice - 1);

            System.out.print("Enter the new Daily Rate: $");
            double newDailyRate = scanner.nextDouble();

            selectedCar.updateDailyRate(newDailyRate);
            System.out.println("Daily Rate Updated Successfully!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }


    public static void main(String[] args) {
        CarRentals rentalSystem = new CarRentals();
        rentalSystem.initializeCars();

        int choice;
        do {
            rentalSystem.displayMenu();
            choice = rentalSystem.scanner.nextInt();

            switch (choice) {
                case 1:
                    rentalSystem.checkVehicleAvailability();
                    break;
                case 2:
                    rentalSystem.bookVehicle();
                    break;
                case 3:
                    rentalSystem.viewBookedVehicles();
                    break;
                case 4:
                    rentalSystem.processPayment();
                    break;
                case 5:
                    rentalSystem.returnVehicles();
                    break;
                case 6:
                    rentalSystem.addNewVehicle();
                    break;
                case 7:
                    rentalSystem.updateDailyRate();
                    break;
                case 8:
                    System.out.println("Exiting Car Rental System. Goodbye Come Again!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);

        rentalSystem.scanner.close();
    }
}