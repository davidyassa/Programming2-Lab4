/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {

    private final String customerSSN;
    private final String productID;
    private final LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate, boolean paid) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = paid;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String lineRepresentation() { // returns the data of the object comma separated.
        String Date = purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String line = String.join(",",
                customerSSN,
                productID,
                Date,
                String.valueOf(paid)
        );
        return line;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getSearchKey() {
        String Date = purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String line = String.join(",",
                customerSSN,
                productID,
                Date
        );
        return line;
    }
}
