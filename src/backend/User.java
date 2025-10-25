/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author HP
 */
public class User implements Recordable {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String lineRepresentation() {
        return username + "," + password;
    }

    @Override
    public String getSearchKey() {
        return username;
    }

    // getPassword() is a security breach
    public boolean isCorrectPassword(String input) {
        return password.equals(input);
    }

}
