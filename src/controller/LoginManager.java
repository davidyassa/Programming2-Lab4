/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import backend.*;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class LoginManager {

    private static UserDatabase db;

    public LoginManager(String filename) throws IOException {
        db = new UserDatabase(filename);
        db.readFromFile();
    }

    public int login(String user, String pass) {
        User u;
        if (db.contains(user)) {
            u = db.getRecord(user);
            if (u.isCorrectPassword(pass)) {
                return 0; // Login Success
            } else {
                return 1; // incorrect password
            }
        } else {
            return -1; //incorrect username
        }
    }

    public boolean addUser(String user, String pass) {
        if (db.contains(user)) {
            return false;
        }
        User u = new User(user, pass);
        db.insertRecord(u);
        return true;
    }

    public void removeUser(String user) {
        db.deleteRecord(user);
    }

    public static void loadData() throws IOException {
        db.readFromFile();
    }

    public static void saveData() throws IOException {
        db.saveToFile();
    }

}
