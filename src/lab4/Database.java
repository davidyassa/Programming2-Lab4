/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.ArrayList;
import java.io.IOException;

/**
 * Generic abstract database class for managing records of type T.
 *
 * @param <T> EmployeeUser, Product, and CustomerProduct
 */
public abstract class Database<T> {
    protected ArrayList<T> Users;
    protected String filename;

    public Database(String filename) {
        this.filename = filename;
    }
    
    public abstract void readFromFile() throws IOException;
    public abstract T createRecordFrom(String line);
    public ArrayList<T> returnAllRecords(){
        return Users;
    }
    public abstract boolean contains(String key);
    public abstract T getRecord(String key);
    public void insertRecord(T record){
        Users.add(record);
    }
    public abstract void deleteRecord(String key);
    public abstract void saveToFile() throws IOException;
}
