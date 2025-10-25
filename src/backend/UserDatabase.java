/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author HP
 */
public class UserDatabase extends Database<User> {

    public UserDatabase(String filename) {
        super(filename);
    }

    @Override
    public User createRecordFrom(String line) {
        String[] tokens = line.split(",");
        int i = 0;
        return new User(tokens[i++], tokens[i++]);
    }

}
