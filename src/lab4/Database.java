/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.*;
import java.util.ArrayList;

/**
 * Generic abstract database class for managing records of type T.
 *
 * @param <T> EmployeeUser, Product, and CustomerProduct, which all implement
 * Recordable
 */
public abstract class Database<T extends Recordable> {

    protected ArrayList<T> data;
    protected String filename;

//    public abstract void readFromFile() throws IOException;
//    public abstract boolean contains(String key);
//    public abstract T getRecord(String key);
//    public abstract void deleteRecord(String key);
//    public abstract void saveToFile() throws IOException;
    public Database(String filename) {
        this.filename = filename;
        data = new ArrayList<>();
    }

    public void readFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                insertRecord(createRecordFrom(line));
            }
        }
    }

    public abstract T createRecordFrom(String line);

    public ArrayList<T> returnAllRecords() {
        return data;
    }

    public boolean contains(String key) {
        for (int i = 0; i < data.size(); i++) {
            T t = data.get(i);
            if (t.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public T getRecord(String key) {
        for (int i = 0; i < data.size(); i++) {
            T t = data.get(i);
            if (t.getSearchKey().equals(key)) {
                return t;
            }
        }
        return null;
    }

    public void insertRecord(T record) {
        data.add(record);
    }

    public void deleteRecord(String key) {

        data.removeIf(t -> t.getSearchKey().equals(key));
    }

    public void saveToFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) { //try automatically calls writer.close()
            for (T t : data) {
                writer.println(t.lineRepresentation());
            }
        }
    }
}
