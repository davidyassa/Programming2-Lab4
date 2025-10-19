/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.IOException;
import java.util.Comparator;

public class ProductDatabase extends Database<Product> {

    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        String[] parts = line.split(",");

        if (parts.length == 6) {
            String id = parts[0];
            String name = parts[1];
            String manufacturer = parts[2];
            String supplier = parts[3];
            int quantity = Integer.parseInt(parts[4]);
            float price = Float.parseFloat(parts[5]);

            Product product = new Product(id, name, manufacturer, supplier, quantity, price);
            return product;
        }
        return null;
    }

    @Override
    public void saveToFile() throws IOException {
        data.sort(Comparator.comparing(Product::getSearchKey));
        super.saveToFile();
    }
}
