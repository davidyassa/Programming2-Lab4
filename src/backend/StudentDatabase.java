/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.IOException;

public class StudentDatabase extends Database<Student> {

    public StudentDatabase(String filename) {
        super(filename);
    }

    @Override
    public Student createRecordFrom(String line) {
        String[] tokens = line.split(",");
        int i = 0;
        int StudentID = Integer.parseInt(tokens[i++]);
        String name = tokens[i++];
        int age = Integer.parseInt(tokens[i++]);
        String gender = tokens[i++];
        String department = tokens[i++];
        float GPA = Float.parseFloat(tokens[i++]);
        return new Student(StudentID, name, age, gender, department, GPA);
    }

    public static int generateID(String filename) throws IOException {
        StudentDatabase db = new StudentDatabase(filename);
        db.readFromFile();
        int temp = 999;
        for (Student s : db.returnAllRecords()) {
            if (s.getStudentID() > temp) {
                temp = s.getStudentID();
            }
        }
        return temp + 1;
    }

    public boolean contains(int key) {
        for (Student s : data) {
            if (s.getStudentID() == key) {
                return true;
            }
        }
        return false;
    }

    public Student getRecord(int key) {
        for (Student s : data) {
            if (s.getStudentID() == key) {
                return s;
            }
        }
        return null;
    }

    public void deleteRecord(int key) {
        data.removeIf(s -> s.getStudentID() == key);
    }
}
