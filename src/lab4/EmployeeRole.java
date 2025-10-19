package lab4;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.io.IOException;

public class EmployeeRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public ProductDatabase getProductsDatabase() {
        return productsDatabase;
    }

    public CustomerProductDatabase getCustomerProductDatabase() {
        return customerProductDatabase;
    }

    public EmployeeRole() throws IOException {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) throws IOException {
        Product product = new Product(productID.trim(), productName.trim(), manufacturerName.trim(), supplierName.trim(), quantity, price);
        if(productsDatabase.contains(product.getSearchKey())){
            return;
        }
        productsDatabase.insertRecord(product);
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) throws IOException {
        float defaultPrice = 0.0f;
        addProduct(productID, productName, manufacturerName, supplierName, quantity, defaultPrice);
    }

    public Product[] getListOfProducts() {
        ArrayList<Product> list = productsDatabase.returnAllRecords();
        return list.toArray(new Product[0]);
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
        return list.toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) throws IOException {
        if (!productsDatabase.contains(productID)) {
            return false;
        }

        Product product = productsDatabase.getRecord(productID);
        if (product.getQuantity() == 0) {
            return false;
        }

        product.setQuantity(product.getQuantity() - 1);
        boolean paid = false;
        CustomerProduct purchase = new CustomerProduct(customerSSN.trim(), productID.trim(), purchaseDate, paid);
        customerProductDatabase.insertRecord(purchase);
        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) throws IOException {
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }
        if (!productsDatabase.contains(productID)) {
            return -1;
        }

        String key = customerSSN.trim() + "," + productID.trim() + "," + formatDate(purchaseDate);
        if (!customerProductDatabase.contains(key)) {
            return -1;
        }

        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (days > 14) {
            return -1;
        }

        Product product = productsDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(key);

        String line = product.lineRepresentation();
        String[] parts = line.split(",");
        if (parts.length == 6) {
            try {
                return Double.parseDouble(parts[5]);
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return -1;
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) throws IOException {
        String targetDate = formatDate(purchaseDate);
        for (CustomerProduct cp : customerProductDatabase.returnAllRecords()) {
            if (cp.getCustomerSSN().equals(customerSSN.trim()) && formatDate(cp.getPurchaseDate()).equals(targetDate) && !cp.isPaid()) {
                cp.setPaid(true);
                return true;
            }
        }
        return false;
    }

    public void logout() throws IOException {
        saveFiles();
    }

    public void readFiles() throws IOException {
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void saveFiles() throws IOException {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
    private String formatDate(LocalDate date) {
        return String.format("%d-%d-%d", date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }
}