/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
               
package frontend;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class LoginFrame extends MainFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        super();
        this.setLayout(null);

        JLabel loginword = new JLabel("LOGIN");
        loginword.setBounds(400, 25, 200, 60);
        this.add(loginword);

        JLabel username = new JLabel("Username:");
        username.setBounds(100, 100, 200, 60);
        this.add(username);

        JLabel password = new JLabel("Password:");
        password.setBounds(100, 200, 200, 60);
        this.add(password);

        usernameField = new JTextField();
        usernameField.setBounds(300, 110, 300, 40);
        this.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 220, 300, 40);
        this.add(passwordField);

        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(350, 300, 150, 40);
        this.add(loginButton);

        
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = usernameField.getText().trim();
                String passwordInput = new String(passwordField.getPassword()).trim();

                if (checkdentials(usernameInput, passwordInput)) {
                    JOptionPane.showMessageDialog(null, " Login successful!");
                } else {
                    JOptionPane.showMessageDialog(null, " Invalid username or password.");
                }
            }
        });

        this.setVisible(true);
    }

   
    private boolean checkdentials(String username, String password) {
        

        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUsername = parts[0].trim();
                    String storedPassword = parts[1].trim();

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }

        return false;
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
