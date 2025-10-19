/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Generic abstract database class for managing records of type T.
 *
 * @param <T> EmployeeUser, Product, and CustomerProduct, which all implement
 * Recordable
 */
public abstract class Database<T extends Recordable> {

    protected ArrayList<T> Data;
    protected String filename;

//    public abstract void readFromFile() throws IOException;
//    public abstract boolean contains(String key);
//    public abstract T getRecord(String key);
//    public abstract void deleteRecord(String key);
//    public abstract void saveToFile() throws IOException;
    public Database(String filename) {
        this.filename = filename;
        Data = new ArrayList<>();
    }

    public void readFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                insertRecord(createRecordFrom(line));
            }
        }
        Data.sort(Comparator.comparing(T::getSearchKey));
    }

    public abstract T createRecordFrom(String line);

    public ArrayList<T> returnAllRecords() {
        return Data;
    }

    public boolean contains(String key) {
        for (T t : Data) {
            if (t.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public T getRecord(String key) {
        if (contains(key)) {
            for (T t : Data) {
                if (t.getSearchKey().equals(key)) {
                    return t;
                }
            }
        }
        return null;
    }

    public void insertRecord(T record) {
        Data.add(record);
    }

    public void deleteRecord(String key) {

        Data.removeIf(t -> t.getSearchKey().equals(key));
    }

    public void saveToFile() throws IOException {
//        Data.sort(Comparator.comparing(T::getSearchKey));
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) { //try automatically calls pw.close()
            for (T t : Data) {
                pw.println(t.lineRepresentation());
            }
        }
    }
}
