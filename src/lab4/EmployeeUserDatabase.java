/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.io.IOException;
import java.util.Comparator;

/*
E1200,Ahmed,ahmed_1999@gmail.com,Alexandria,01088877345 
Note: The employee id is unique for each employee. 
 */
public class EmployeeUserDatabase extends Database<EmployeeUser> { // for reading from and writing to Employees.txt

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
        int i = 0;
        String[] tokens = line.split(",");
        return new EmployeeUser(tokens[i++], tokens[i++], tokens[i++], tokens[i++], tokens[i++]);
    }

    @Override
    public void saveToFile() throws IOException {
        data.sort(Comparator.comparing(EmployeeUser::getSearchKey));
        super.saveToFile();
    }
}
