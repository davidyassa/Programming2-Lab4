/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeerolelab4;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public ProductDatabase getProductsDatabase() {
        return productsDatabase;
    }

    public CustomerProductDatabase getCustomerProductDatabase() {
        return customerProductDatabase;
    }

    
    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        readFiles();
    }

    // Add new product
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        Product product = new Product(productID.trim(), productName.trim(), manufacturerName.trim(), supplierName.trim(), quantity, 0f);
        productsDatabase.insertRecord(product);
    }

    // 2
    public Product[] getListOfProducts() {
        ArrayList<Product> list = productsDatabase.returnAllRecords();
        return list.toArray(new Product[0]);
    }

    // 3
    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
        return list.toArray(new CustomerProduct[0]);
    }

    // 4 
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (!productsDatabase.contains(productID)) return false;

        Product product = productsDatabase.getRecord(productID);
        if (product.getQuantity() == 0) return false;

        product.setQuantity(product.getQuantity() - 1);
        CustomerProduct purchase = new CustomerProduct(customerSSN.trim(), productID.trim(), purchaseDate);
        customerProductDatabase.insertRecord(purchase);
        return true;
    }

    // 5
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) return -1;
        if (!productsDatabase.contains(productID)) return -1;

        String key = customerSSN.trim() + "," + productID.trim() + "," + formatDate(purchaseDate);
        if (!customerProductDatabase.contains(key)) return -1;

        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (days > 14) return -1;

        Product product = productsDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(key);
        return product.getPrice();
    }

    // 6
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        String targetDate = formatDate(purchaseDate);
        for (CustomerProduct cp : customerProductDatabase.returnAllRecords()) {
            if (cp.getCustomerSSN().equals(customerSSN.trim()) &&
                formatDate(cp.getPurchaseDate()).equals(targetDate) &&
                !cp.isPaid()) {
                cp.setPaid(true);
                return true;
            }
        }
        return false;
    }

    // 7
    public void logout() {
        saveFiles();
    }

    // Read data
    public void readFiles() {
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    // Save data
    public void saveFiles() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }

    // Format date 
    private String formatDate(LocalDate date) {
        return String.format("%d-%d-%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
}

