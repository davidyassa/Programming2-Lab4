/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.*;

/*
E1200,Ahmed,ahmed_1999@gmail.com,Alexandria,01088877345 
Note: The employee id is unique for each employee. 
 */
public class EmployeeUserDatabase extends Database<EmployeeUser> { // for reading from and writing to Employees.txt

    private final ArrayList<EmployeeUser> EmployeeUsers;

    public EmployeeUserDatabase(String filename) {
        super(filename);
        EmployeeUsers = new ArrayList<>(); // no need for <EmployeeUser>();
    }

    @Override
    public void readFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                insertRecord(createRecordFrom(line));
            }
        }
        EmployeeUsers.sort(Comparator.comparing(EmployeeUser::getSearchKey));
    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
        int i = 0;
        String[] tokens = line.split(",");
        return new EmployeeUser(tokens[i++], tokens[i++], tokens[i++], tokens[i++], tokens[i++]);
    }

    @Override
    public boolean contains(String key) {
        for (EmployeeUser emp : EmployeeUsers) {
            if (emp.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public EmployeeUser getRecord(String key) {
        if (contains(key)) {
            for (EmployeeUser emp : EmployeeUsers) {
                if (emp.getSearchKey().equals(key)) {
                    return emp;
                }
            }
        }
        return null;
    }

    @Override
    public void deleteRecord(String key) {
        EmployeeUsers.removeIf(emp -> emp.getSearchKey().equals(key));
    }

    @Override
    public void saveToFile() throws IOException {
        EmployeeUsers.sort(Comparator.comparing(EmployeeUser::getSearchKey));
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) { //try automatically calls pw.close()
            for (EmployeeUser emp : EmployeeUsers) {
                pw.println(emp.lineRepresentation());
            }
        }
    }
}
