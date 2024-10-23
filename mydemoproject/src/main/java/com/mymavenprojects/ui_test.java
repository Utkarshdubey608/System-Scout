package com.mymavenprojects;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ui_test {

    public static void main(String[] args) {    
        // Creating instance of JFrame
        JFrame frame = new JFrame("SYSTEM SCOUT");
        // Setting the width and height of frame
        frame.setSize(700, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating panel and adding it to the frame
        JPanel panel = new JPanel();    
        frame.add(panel);

        // Setting background color of the panel
        panel.setBackground(Color.BLACK);

        // Adding components to the panel
        placeComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        // Setting the layout to null
        panel.setLayout(null);

        // Creating JLabel for username and setting its text color
        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(210, 395, 80, 40);
        userLabel.setForeground(Color.GREEN);  // Set the text color to white
        userLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        panel.add(userLabel);

        // Creating text field for user name
        JTextField userText = new JTextField(20);
        userText.setForeground(Color.WHITE);
        userText.setBackground(Color.BLACK);
        userText.setBounds(270, 400, 165, 40);
        panel.add(userText);

        // Creating JLabel for password and setting its text color
        JLabel passwordLabel = new JLabel("Pass:");
        passwordLabel.setBounds(205, 465, 80, 40);
        passwordLabel.setForeground(Color.GREEN);  // Set the text color to white
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        panel.add(passwordLabel);

        // Creating password field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setForeground(Color.WHITE);
        passwordText.setBackground(Color.BLACK);
        passwordText.setBounds(270, 470, 165, 40);
        panel.add(passwordText);

        // Creating login button
        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.GREEN);
        loginButton.setBackground(Color.BLACK);
        loginButton.setBounds(310, 550, 80, 25);
        panel.add(loginButton);

        // Load an image from file (Ensure the file path is correct)
        ImageIcon imageIcon = new ImageIcon("C:\\codes\\aoodp_maven_project(try)beta\\mydemoproject\\src\\main\\java\\com\\mymavenprojects\\assets\\icon_profile.png");  // Replace with your image path
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(130, 50, 400, 400);
        panel.add(imageLabel);

        // Adding action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the userText and passwordText fields
                String username = userText.getText();
                String password = new String(passwordText.getPassword());  // getPassword() returns a char array

                // Display the input in the console
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }
        });
    }
}
