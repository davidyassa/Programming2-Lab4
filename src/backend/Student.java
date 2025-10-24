/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * • Student ID (unique integer, automatically generated or entered by the user)
 * • Full Name • Age • Gender (Male/Female) • Department • GPA or Grade
 *
 */
public class Student implements Recordable {

    private int studentID;
    private String name;
    private int age;
    private String gender;
    private String department;
    private float GPA;
    private static int defaultID = 1000;

    public Student(int StudentID, String name, int age, String gender, String department, float GPA) {
        this.studentID = StudentID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.GPA = GPA;
    }

    public Student(String name, int age, String gender, String department, float GPA) {
        this.studentID = defaultID++;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.GPA = GPA;
    }

    @Override
    public String lineRepresentation() {
        return studentID + "," + name + "," + age + "," + gender + "," + department + "," + GPA;
    }

    // all setters available for updateStudent()
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public static void setDefaultID(int defaultID) {
        Student.defaultID = defaultID;
    }

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }

}
