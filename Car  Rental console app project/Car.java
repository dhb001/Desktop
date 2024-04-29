import java.io.FileWriter;
import java.util.Scanner;

public class Car extends Vehicle {

    private int numDoors;
    private boolean isConvertible;
    private String fuelType;

    public Car(String make, String model, int year, double rentalRate, int numDoors, boolean isConvertible, String fuelType) {
        super(make, model, year, rentalRate);
        this.numDoors = numDoors;
        this.isConvertible = isConvertible;
        this.fuelType = fuelType;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Doors: " + numDoors);
        System.out.println("Convertible: " + isConvertible);
        System.out.println("Fuel Type: " + fuelType);
    }

    // New method to input car information and save to a text file
    public void addNewCar(String fileName) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Make: ");
        String make = scanner.nextLine();

        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after int input

        System.out.print("Enter Rental Rate: ");
        double rentalRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character after double input

        System.out.print("Enter Number of Doors: ");
        numDoors = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after int input

        System.out.print("Is Convertible (true/false): ");
        isConvertible = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline character after boolean input

        System.out.print("Enter Fuel Type: ");
        fuelType = scanner.nextLine();

        // Write car information to a text file
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(this.toString() + "\n"); // Call toString() to get car details in a formatted string
        writer.close();

        System.out.println("New car information saved to " + fileName);
    }

    @Override
    public String toString() {
        return super.toString() + ", Doors: " + numDoors + ", Convertible: " + isConvertible + ", Fuel Type: " + fuelType;
    }
}