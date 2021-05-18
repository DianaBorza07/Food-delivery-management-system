package presentationLayer;

import bussinesLayer.User;
import dataLayer.Serializator;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateNewAccountGUI{

    private JFrame frmCreateNewAccount;
    private JTextField tfUser;
    private JPasswordField tfPassword;
    private List<User> users = new ArrayList<User>();

    public CreateNewAccountGUI() {
        Serializator serializator = new Serializator();
        try {
            users = (List<User>) serializator.deserializable("users.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        initialize();
    }

    private void initialize() {
        frmCreateNewAccount = new JFrame();
        frmCreateNewAccount.setTitle("Create new account");
        frmCreateNewAccount.setBounds(100, 100, 450, 335);
        frmCreateNewAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCreateNewAccount.setVisible(true);
        frmCreateNewAccount.getContentPane().setBackground(new Color(255, 240, 245));
        frmCreateNewAccount.getContentPane().setLayout(null);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e->{
            LoginGUI loginGUI = new LoginGUI();
            frmCreateNewAccount.dispose();
        });
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnBack.setBounds(20, 15, 85, 21);
        frmCreateNewAccount.getContentPane().add(btnBack);

        JLabel lblTitle = new JLabel("Create new account");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitle.setBounds(131, 10, 223, 25);
        frmCreateNewAccount.getContentPane().add(lblTitle);

        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUser.setBounds(32, 91, 91, 13);
        frmCreateNewAccount.getContentPane().add(lblUser);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(32, 137, 78, 13);
        frmCreateNewAccount.getContentPane().add(lblPassword);

        JLabel lblType = new JLabel("Type of user");
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblType.setBounds(32, 175, 91, 19);
        frmCreateNewAccount.getContentPane().add(lblType);

        tfUser = new JTextField();
        tfUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfUser.setBounds(169, 90, 254, 19);
        frmCreateNewAccount.getContentPane().add(tfUser);
        tfUser.setColumns(10);

        tfPassword = new JPasswordField();
        tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfPassword.setBounds(169, 136, 254, 19);
        frmCreateNewAccount.getContentPane().add(tfPassword);
        tfPassword.setColumns(10);

        JComboBox comboTypeUser = new JComboBox();
        comboTypeUser.setBounds(169, 176, 254, 21);
        comboTypeUser.setModel(new DefaultComboBoxModel(new String[] {"Administrator","Regular employee","Client"}));
        frmCreateNewAccount.getContentPane().add(comboTypeUser);


        JButton btnCreate = new JButton("Create account");
        btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCreate.setBounds(141, 229, 167, 46);
        frmCreateNewAccount.getContentPane().add(btnCreate);
        btnCreate.addActionListener(e ->{
            if(tfUser.getText().isBlank() || tfPassword.getText().isBlank() )
                JOptionPane.showMessageDialog(null,"Insert username and password");
            else{
                int userType = 0;
                if(comboTypeUser.getSelectedItem() == "Administrator") userType = 1;
                if(comboTypeUser.getSelectedItem() == "Client") userType = 2;
                if(comboTypeUser.getSelectedItem() == "Regular employee") userType =3;
                User newUser = new User(users.size()+1,userType,tfUser.getText().toString(),tfPassword.getText().toString());
                users.add(newUser);
                Serializator serializator = new Serializator();
                try {
                    serializator.serializable("users.txt",users);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Account created successfully");
                LoginGUI loginGUI = new LoginGUI();
                frmCreateNewAccount.dispose();
            }

        });
    }
}
