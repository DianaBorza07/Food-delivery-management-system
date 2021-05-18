package presentationLayer;

import bussinesLayer.DeliveryService;
import bussinesLayer.User;
import dataLayer.Serializator;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginGUI {

    private JFrame frmFoodDeliveryManagement;
    private JTextField tfUser;
    private JPasswordField tfPassword;
    private DeliveryService deliveryService ;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginGUI window = new LoginGUI();
                    window.frmFoodDeliveryManagement.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginGUI() {
        deliveryService = new DeliveryService();
        Serializator s = new Serializator();
        try {
            deliveryService = (DeliveryService) s.deserializable("deliveryServiceInfo.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        deliveryService.getBaseProducts().clear();
        deliveryService.setBaseProductID(1);
        deliveryService.getMenuItems().clear();
        initialize();
    }

    private void initialize() {
        frmFoodDeliveryManagement = new JFrame();
        frmFoodDeliveryManagement.setTitle("Food delivery management system");
        frmFoodDeliveryManagement.getContentPane().setBackground(new Color(230, 230, 250));
        frmFoodDeliveryManagement.setBounds(100, 100, 540, 474);
        frmFoodDeliveryManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmFoodDeliveryManagement.getContentPane().setLayout(null);
        frmFoodDeliveryManagement.setVisible(true);

        JLabel lblTitle = new JLabel("FOOD DELIVERY MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblTitle.setBounds(89, 27, 374, 27);
        frmFoodDeliveryManagement.getContentPane().add(lblTitle);

        JLabel lblUser = new JLabel("User");
        lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblUser.setBounds(48, 148, 58, 13);
        frmFoodDeliveryManagement.getContentPane().add(lblUser);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPassword.setBounds(48, 188, 76, 13);
        frmFoodDeliveryManagement.getContentPane().add(lblPassword);

        tfUser = new JTextField();
        tfUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfUser.setBounds(200, 147, 240, 19);
        frmFoodDeliveryManagement.getContentPane().add(tfUser);
        tfUser.setColumns(10);

        tfPassword = new JPasswordField();
        tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tfPassword.setBounds(200, 187, 240, 19);
        frmFoodDeliveryManagement.getContentPane().add(tfPassword);
        tfPassword.setColumns(10);

        JButton btnLogin = new JButton("Log in");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfUser.getText().isBlank() || tfPassword.getText().isBlank())
                    JOptionPane.showMessageDialog(null, "Insert username and password");
                else {
                    String username = tfUser.getText().toString();
                    String password = tfPassword.getText().toString();
                    Serializator serializator = new Serializator();
                    List<User> users = new ArrayList<>();
                    try {
                        users = (List<User>) serializator.deserializable("users.txt");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    int typeOfUser = 0; int userID = 0;
                    for (User user : users
                    ) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            typeOfUser = user.getUserType();
                            userID = user.getUserID(); } }
                    if(typeOfUser == 1) { AdministratorGUI administratorGUI = new AdministratorGUI(deliveryService); }
                    if(typeOfUser == 2) { ClientGUI client = new ClientGUI(deliveryService,userID); }
                    if(typeOfUser == 3) { EmployeeGUI employee = new EmployeeGUI(deliveryService); }
                    frmFoodDeliveryManagement.dispose(); } }
        });
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLogin.setBounds(199, 253, 119, 38);
        frmFoodDeliveryManagement.getContentPane().add(btnLogin);

        JLabel lblNewAccount = new JLabel("You don't have an account? Create one!");
        lblNewAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewAccount.setBounds(238, 357, 263, 13);
        frmFoodDeliveryManagement.getContentPane().add(lblNewAccount);

        JButton btnCreateAccount = new JButton("Create account");
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateNewAccountGUI account = new CreateNewAccountGUI();
                frmFoodDeliveryManagement.dispose();
            }
        });
        btnCreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnCreateAccount.setBounds(295, 387, 145, 21);
        frmFoodDeliveryManagement.getContentPane().add(btnCreateAccount);
    }
}

