/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author HP
 */
public class ProductDatabase extends Database<Product> {

    private ArrayList<Product> records;

    public ProductDatabase(String filename) {

        super(filename);
        records = new ArrayList<>();
    }

    public void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Product.txt"));
            String line;
            records = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 6) {
                    String id = parts[0];
                    String name = parts[1];
                    String manufacturer = parts[2];
                    String supplier = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    float price = Float.parseFloat(parts[5]);

                    Product product = new Product(id, name, manufacturer, supplier, quantity, price);
                    records.add(product);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public ArrayList<Product> returnAllRecords() {

        return new ArrayList<>(records);

    }

    public boolean contains(String key) {
        for (int i = 0; i < records.size(); i++) {
            Product p2 = records.get(i);
            if (key.equals(p2.getSearchKey())) {
                return true;
            }
        }
        return false;
    }

    public Product getRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            Product p3 = records.get(i);
            if (p3.getSearchKey().equals(key)) {
                return p3;
            }
        }
        return null;
    }

    public void insertRecord(Product record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        for (int i = 0; i < records.size(); i++) {
            //Product p2 = records.get(i);
            if (records.get(i).getSearchKey().equals(key)) {
                records.remove(i);
            }
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < records.size(); i++) {
                Product p = records.get(i);
                writer.write(p.getobject());
                writer.newLine();
            }
            System.out.println("Products written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}
