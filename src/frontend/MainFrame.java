/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;
import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        this.setTitle("Student Management System");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Show the login screen first
        setContentPane(new LoginFrame(this));

        this.setVisible(true);
    }

    public void switchPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

   
}
