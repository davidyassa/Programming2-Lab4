/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author HP
 */
public class CustomerProductDatabase extends Database<CustomerProduct> {

    private final ArrayList<CustomerProduct> CustomerProducts;

    public CustomerProductDatabase(String filename) {
        super(filename);
        CustomerProducts = new ArrayList<>(); // no need for <EmployeeUser>();
    }

    @Override
    public void readFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                insertRecord(createRecordFrom(line));
            }
        }
        CustomerProducts.sort(Comparator.comparing(CustomerProduct::getPurchaseDate));
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        int i = 0;
        String[] tokens = line.split(",");
        String customerSSN = tokens[i++];
        String productID = tokens[i++];
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate purchaseDate = LocalDate.parse(tokens[i++], fmt);
        boolean paid = Boolean.parseBoolean(tokens[i++]);

        return new CustomerProduct(customerSSN, productID, purchaseDate, paid);
    }

    @Override
    public boolean contains(String key) {
        for (CustomerProduct cp : CustomerProducts) {
            String combine = cp.getCustomerSSN() + "," + cp.getProductID() + "," + cp.getPurchaseDate();
            if (combine.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CustomerProduct getRecord(String key) {
        if (contains(key)) {
            for (CustomerProduct cp : CustomerProducts) {
                String combine = cp.getCustomerSSN() + "," + cp.getProductID() + "," + cp.getPurchaseDate();
                if (combine.equals(key)) {
                    return cp;
                }
            }
        }
        return null;
    }

    @Override
    public void deleteRecord(String key) {

        CustomerProducts.removeIf(cp -> cp.getSearchKey().equals(key));
    }

    @Override
    public void saveToFile() throws IOException {
        CustomerProducts.sort(Comparator.comparing(CustomerProduct::getSearchKey));
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) { //try automatically calls pw.close()
            for (CustomerProduct cp : CustomerProducts) {
                pw.println(cp.lineRepresentation());
            }
        }
    }
}
