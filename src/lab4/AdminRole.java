/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author HP
 */
   
    import java.util.ArrayList;

public class AdminRole {

    private final EmployeeUserDatabase database;

    public AdminRole() {
        database = new EmployeeUserDatabase("Employees.txt");
        try {
            database.readFromFile();
        } catch (Exception e) {
            System.err.println("Error reading employees file: " + e.getMessage());
        }
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        if (isNullOrEmpty(employeeId) || isNullOrEmpty(name) || isNullOrEmpty(email)
                || isNullOrEmpty(address) || isNullOrEmpty(phoneNumber)) {
            System.err.println("Error: All employee fields must be filled.");
            return;
        }

        try {
            if (database.contains(employeeId)) {
                System.err.println("Error: Employee with ID " + employeeId + " already exists.");
                return;
            }
            EmployeeUser newEmployee = new EmployeeUser(employeeId.trim(), name.trim(), email.trim(),
                                                        address.trim(), phoneNumber.trim());
            database.insertRecord(newEmployee);
            System.out.println("Employee added successfully.");
        } catch (Exception e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }

    public EmployeeUser[] getListOfEmployees() {
        try {
            ArrayList<EmployeeUser> list = database.returnAllRecords();
            return list.toArray(new EmployeeUser[0]);
        } catch (Exception e) {
            System.err.println("Error retrieving employee list: " + e.getMessage());
            return new EmployeeUser[0];
        }
    }

    public void removeEmployee(String key) {
        if (isNullOrEmpty(key)) {
            System.err.println("Error: Employee ID cannot be empty.");
            return;
        }

        try {
            if (!database.contains(key)) {
                System.err.println("Error: Employee not found.");
                return;
            }
            database.deleteRecord(key);
            System.out.println("Employee removed successfully.");
        } catch (Exception e) {
            System.err.println("Error removing employee: " + e.getMessage());
        }
    }

    public void logout() {
        try {
            database.saveToFile();
            System.out.println("All changes saved successfully.");
        } catch (Exception e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}

}
