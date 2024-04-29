import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginRegisterApp {

  private static final String USER_DATA_FILE = "users.txt";
  private static final String CAR_DATA_FILE = "cars.txt";
  private static final Scanner scanner = new Scanner(System.in);
  private static Map<String, String> users = new HashMap<>();

  public static void main(String[] args) {
    loadData();
    displayMenu();
  }

  private static void loadData() {
    // Load user data
    try (Scanner fileScanner = new Scanner(new File(USER_DATA_FILE))) {
      while (fileScanner.hasNextLine()) {
        String line = fileScanner.nextLine();
        String[] userCredentials = line.split(",");
        users.put(userCredentials[0], userCredentials[1]);
      }
    } catch (FileNotFoundException e) {
      System.out.println("User data file not found. Creating a new one.");
    } catch (IOException e) {
      System.out.println("Error reading user data file: " + e.getMessage());
    }
  }

  private static void saveData() {
    // Save user data
    try (FileWriter writer = new FileWriter(USER_DATA_FILE)) {
      for (Map.Entry<String, String> entry : users.entrySet()) {
        writer.write(entry.getKey() + "," + entry.getValue() + "\n");
      }
    } catch (IOException e) {
      System.out.println("Error saving user data: " + e.getMessage());
    }
  }

  private static void displayMenu() {
    int choice;
    do {
      System.out.println("\nLogin and Registration Menu");
      System.out.println("1. Login");
      System.out.println("2. Register");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");

      choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline character

      switch (choice) {
        case 1:
          login();
          break;
        case 2:
          register();
          break;
        case 3:
          System.out.println("Exiting application...");
          break;
        default:
          System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 3);
  }

  private static void login() {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (users.containsKey(username) && users.get(username).equals(password)) {
      System.out.println("Login successful! Welcome " + username);

      if (username.equals("admin") && password.equals("admin")) {
        addCar();
      } else {
        System.out.println("You are not authorized to add cars.");
      }
    } else {
      System.out.println("Invalid username or password.");
    }
  }

  private static void register() {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    if (users.containsKey(username)) {
      System.out.println("Username already exists. Please choose a different username.");
      return;
    }

    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    users.put(username, password);
    saveData();
    System.out.println("Registration successful!");
  }

  private static void addCar() {
    // Implement your logic to add a new car using Car class (assuming it exists)
    // and write the car information to the CAR_DATA_FILE
    System.out.println("Adding a new car (Admin Only)");
    // ... your car input and saving logic here ...
  }
}
