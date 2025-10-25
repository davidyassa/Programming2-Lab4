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

    private static StudentDatabase db;

    public StudentManager(String filename) throws IOException{
        db = new StudentDatabase(filename);
        db.readFromFile();
    }

    public static void addStudent(Student s) throws IOException {
        db.insertRecord(s);
    }

    public static Student search(int id) {
        return db.getRecord(id);
    }

    public static Student search(String name) {
        return db.getRecord(name);
    }

    public static void deleteStudent(int id) throws IOException {
        db.deleteRecord(id);
    }

    public static void deleteStudent(String Name) throws IOException {
        db.deleteRecord(Name);
    }

    public static void sortByName() {
        db.returnAllRecords().sort(Comparator.comparing(Student::getSearchKey));
    }

    public static void sortByID() {
        db.returnAllRecords().sort(Comparator.comparing(Student::getStudentID));
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
