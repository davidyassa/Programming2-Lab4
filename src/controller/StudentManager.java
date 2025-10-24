/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import backend.*;
import java.io.IOException;
import java.util.Comparator;

/**
 *
 * @author HP
 */
public final class StudentManager {

    private final static StudentDatabase db = new StudentDatabase("Students.txt");

    public static void addStudent(Student s) throws IOException {
        db.insertRecord(s);
    }

    public static Student searchByID(int id) {
        return db.getRecord(id);
    }
    public static Student searchByName(String name) {
        return db.getRecord(name);
    }

    public static void deleteStudent(int id) throws IOException {
        db.deleteRecord(id);
    }

    public static void sortByName() {
        db.returnAllRecords().sort(Comparator.comparing(Student::getName));
    }

    public static void sortByID() {
        db.returnAllRecords().sort(Comparator.comparing(Student::getStudentID));
    }

    public static int generateID() {
        if (db.returnAllRecords().isEmpty()) {
            return 1000;
        }
        int temp = db.returnAllRecords().get(0).getStudentID();
        for (Student s : db.returnAllRecords()) {
            if (s.getStudentID() > temp) {
                temp = s.getStudentID();
            }
        }
        return temp + 1;
    }

    public static void updateStudent(Student updated) { //no updating StudentID
        db.deleteRecord(updated.getStudentID());
        db.insertRecord(updated);
    }

    public static void loadData() throws IOException {
        db.readFromFile();
    }

    public static void saveData() throws IOException {
        db.saveToFile();
    }
}
