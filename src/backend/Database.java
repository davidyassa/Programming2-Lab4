/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.*;
import java.util.ArrayList;

/**
 * Generic abstract database class for managing records of type T.
 *
 * @param <T> Student, User, all of which implement Recordable
 */
public abstract class Database<T extends Recordable> {

    protected ArrayList<T> data;
    protected String filename;

    public abstract T createRecordFrom(String line);

    public T getRecord(String key) {
        for (T t : data) {
            if (t.getSearchKey().equals(key)) {
                return t;
            }
        }
        return null;
    }

    public void deleteRecord(String key) {
        data.removeIf(t -> t.getSearchKey().equals(key));
    }

    public boolean contains(String key) {
        if (key == null) return false;
        for (T t : data) {
            if (t.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

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

    public ArrayList<T> returnAllRecords() {
        return data;
    }

    public void insertRecord(T record) {
        data.add(record);
    }

    public void saveToFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) { //try() automatically calls writer.close()
            for (T t : data) {
                writer.println(t.lineRepresentation());
            }
        }
    }
}
