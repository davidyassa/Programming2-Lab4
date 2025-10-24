/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

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

    @Override
    public boolean contains(String key) {
        for (Student s : data) {
            if (s.getName().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(int key) {
        for (Student s : data) {
            if (s.getStudentID() == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Student getRecord(String key) {
        for (Student s : data) {
            if (s.getName().equals(key)) {
                return s;
            }
        }
        return null;
    }

    public Student getRecord(int key) {
        for (Student s : data) {
            if (s.getStudentID() == key) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void deleteRecord(String key) {
        data.removeIf(s -> s.getName().equals(key));
    }

    public void deleteRecord(int key) {
        data.removeIf(s -> s.getStudentID() == key);
    }
}
