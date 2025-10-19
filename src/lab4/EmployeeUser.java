/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author HP
 */
public class EmployeeUser implements Recordable {

    private final String employeeId;
    private final String Name;
    private final String Email;
    private final String Address;
    private final String PhoneNumber;

    public EmployeeUser(String employeeId, String Name, String Email, String Address, String PhoneNumber) {
        this.employeeId = employeeId;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }

    @Override
    public String lineRepresentation() { //: returns the data of the employee comma separated.
        String line = String.join(",",
                employeeId,
                Name,
                Email,
                Address,
                PhoneNumber);
        return line;
    }

    @Override
    public String getSearchKey() { //: returns the employee id.
        return employeeId;
    }
}
