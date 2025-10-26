/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import javax.swing.*;

public class HomePanel extends JPanel {

    public HomePanel(MainFrame frame) { 
        super();
        setLayout(null);

        JLabel label = new JLabel("Welcome to Student Management System");
        label.setBounds(250, 25, 350, 60);
        add(label);

        JButton add = new JButton("Add Student");
        add.setBounds(300, 100, 150, 40);
        add(add);

        JButton view = new JButton("View Student");
        view.setBounds(300, 180, 150, 40);
        add(view);

        JButton update = new JButton("Update Student");
        update.setBounds(300, 270, 150, 40);
        add(update);

        JButton delete = new JButton("Delete Student");
        delete.setBounds(300, 350, 150, 40);
        add(delete);

        JButton exit = new JButton("Exit");
        exit.setBounds(300, 430, 150, 40);
        add(exit);
        exit.addActionListener(e -> System.exit(0));

       
        add.addActionListener(e -> {
            frame.switchPanel(new  AddStudentPanel(frame));
        });

           update.addActionListener(e -> {
            frame.switchPanel(new SearchUpdatePanel(frame));
        });
        
        view.addActionListener(e -> {
            frame.switchPanel(new ViewStudentPanel (frame));
        });

        delete.addActionListener(e -> {
            frame.switchPanel(new DeletePanel(frame));
        });

    }
}
