/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab4;

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    private static void buffer() {
        System.out.print("\nPress Enter to continue...");
        sc.nextLine();
        System.out.println("\n\n");
    }

    private static final String MENU = """
        ================ MAIN MENU ====================
        1. List all products
        2. List all employees
        3. List all customer-product records
        4. Add a new product
        5. Add a new employee
        6. Add a new customer-product record
        7. Delete a record by key
        8. Save all to files
        0. Exit 
        """;

    public static void main(String[] args) {
        ProductDatabase productDB = new ProductDatabase("Products.txt");
        EmployeeUserDatabase employeeDB = new EmployeeUserDatabase("Employees.txt");
        CustomerProductDatabase customerProductDB = new CustomerProductDatabase("CustomerProducts.txt");

        try {
            productDB.readFromFile();
            employeeDB.readFromFile();
            customerProductDB.readFromFile();
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }

        while (true) {
            System.out.print(MENU);
            try {
                System.out.print("Choose option: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        productDB.returnAllRecords().forEach(p -> System.out.println(p.lineRepresentation()));
                    }
                    case 2 -> {
                        employeeDB.returnAllRecords().forEach(e -> System.out.println(e.lineRepresentation()));
                    }
                    case 3 -> {
                        customerProductDB.returnAllRecords().forEach(c -> System.out.println(c.lineRepresentation()));
                    }
                    case 4 -> {
                        System.out.print("Enter Product ID, Name, Manufacturer, Supplier, Quantity, Price (comma-separated): ");
                        String[] p = sc.nextLine().split(",");
                        productDB.insertRecord(new Product(p[0], p[1], p[2], p[3],
                                Integer.parseInt(p[4]), Float.parseFloat(p[5])));
                        System.out.println("Product added!");
                    }
                    case 5 -> {
                        System.out.print("Enter EmployeeID, Name, Email, Address, Phone (comma-separated): ");
                        String[] e = sc.nextLine().split(",");
                        employeeDB.insertRecord(new EmployeeUser(e[0], e[1], e[2], e[3], e[4]));
                        System.out.println("Employee added!");
                    }
                    case 6 -> {
                        System.out.print("Enter CustomerSSN, ProductID, PurchaseDate(dd-MM-yyyy), Paid(true/false): ");
                        String[] c = sc.nextLine().split(",");
                        customerProductDB.insertRecord(new CustomerProduct(
                                c[0],
                                c[1],
                                LocalDate.parse(c[2],
                                        DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                                Boolean.parseBoolean(c[3])));
                        System.out.println("Customer-product record added!");
                    }
                    case 7 -> {
                        System.out.print("Enter key to delete: ");
                        String key = sc.nextLine();
                        productDB.deleteRecord(key);
                        employeeDB.deleteRecord(key);
                        customerProductDB.deleteRecord(key);
                        System.out.println("Record(s) deleted (if found).");
                    }
                    case 8 -> {
                        productDB.saveToFile();
                        employeeDB.saveToFile();
                        customerProductDB.saveToFile();
                        System.out.println("All data saved successfully!");
                    }
                    case 0 -> {
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    }
                    default -> {
                        System.out.println("Invalid choice, try again.");
                    }
                }

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input format!");
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());

            }
            buffer();
        }
    }
}
