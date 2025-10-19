/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.IOException;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author HP
 */
public class CustomerProductDatabase extends Database<CustomerProduct> {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() throws IOException {
        super.readFromFile();
        Data.sort(Comparator.comparing(CustomerProduct::getPurchaseDate));
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
}
