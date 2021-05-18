package presentationLayer;

import bussinesLayer.BaseProduct;
import bussinesLayer.DeliveryService;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewProductGUI {

    private JFrame frmAddNewProduct;
    private JTextField tfName;
    private JTextField tfRating;
    private JTextField tfCalories;
    private JTextField tfProtein;
    private JTextField tfFat;
    private JTextField tfSodium;
    private JTextField tfPrice;
    private DeliveryService deliveryService;
    private Boolean var;
    private BaseProduct baseProduct;

    public NewProductGUI(DeliveryService service) {
        this.var = false;
        this.deliveryService = service;
        initialize();
    }

    private void initialize() {
        frmAddNewProduct = new JFrame();
        frmAddNewProduct.setTitle("Add new product");
        frmAddNewProduct.getContentPane().setBackground(new Color(250, 250, 210));
        frmAddNewProduct.setBackground(new Color(255, 228, 196));
        frmAddNewProduct.setBounds(100, 100, 450, 311);
        frmAddNewProduct.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frmAddNewProduct.getContentPane().setLayout(null);
        frmAddNewProduct.setVisible(true);

        JLabel lblName = new JLabel("Product name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblName.setBounds(23, 22, 122, 19);
        frmAddNewProduct.getContentPane().add(lblName);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblRating.setBounds(23, 51, 97, 19);
        frmAddNewProduct.getContentPane().add(lblRating);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCalories.setBounds(23, 80, 97, 19);
        frmAddNewProduct.getContentPane().add(lblCalories);

        JLabel lblProtein = new JLabel("Protein");
        lblProtein.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblProtein.setBounds(23, 109, 97, 19);
        frmAddNewProduct.getContentPane().add(lblProtein);

        JLabel lblFat = new JLabel("Fat");
        lblFat.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblFat.setBounds(23, 138, 84, 19);
        frmAddNewProduct.getContentPane().add(lblFat);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSodium.setBounds(23, 167, 97, 19);
        frmAddNewProduct.getContentPane().add(lblSodium);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblPrice.setBounds(23, 196, 97, 19);
        frmAddNewProduct.getContentPane().add(lblPrice);

        tfName = new JTextField();
        tfName.setBounds(175, 24, 187, 19);
        frmAddNewProduct.getContentPane().add(tfName);
        tfName.setColumns(10);

        tfRating = new JTextField();
        tfRating.setBounds(175, 53, 187, 19);
        frmAddNewProduct.getContentPane().add(tfRating);
        tfRating.setColumns(10);

        tfCalories = new JTextField();
        tfCalories.setBounds(175, 82, 187, 19);
        frmAddNewProduct.getContentPane().add(tfCalories);
        tfCalories.setColumns(10);

        tfProtein = new JTextField();
        tfProtein.setBounds(175, 111, 187, 19);
        frmAddNewProduct.getContentPane().add(tfProtein);
        tfProtein.setColumns(10);

        tfFat = new JTextField();
        tfFat.setBounds(175, 140, 187, 19);
        frmAddNewProduct.getContentPane().add(tfFat);
        tfFat.setColumns(10);

        tfSodium = new JTextField();
        tfSodium.setBounds(175, 169, 187, 19);
        frmAddNewProduct.getContentPane().add(tfSodium);
        tfSodium.setColumns(10);

        tfPrice = new JTextField();
        tfPrice.setBounds(175, 198, 187, 19);
        frmAddNewProduct.getContentPane().add(tfPrice);
        tfPrice.setColumns(10);

        JButton btnConfirm = new JButton("Add");
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tfName.getText().isBlank()||tfCalories.getText().isBlank()||tfFat.getText().isBlank()||
                tfPrice.getText().isBlank()||tfProtein.getText().isBlank()||tfRating.getText().isBlank()||tfSodium.getText().isBlank())
                    JOptionPane.showMessageDialog(null,"Fields cannot be empty");
                else{
                    BaseProduct newProduct=new BaseProduct(deliveryService.getBaseProducts().size()+20,Integer.parseInt(tfPrice.getText()),
                            tfName.getText(),Float.parseFloat(tfRating.getText()),Integer.parseInt(tfCalories.getText()),
                            Integer.parseInt(tfProtein.getText()),Integer.parseInt(tfFat.getText()),Integer.parseInt(tfSodium.getText()));
                    deliveryService.getMenuItems().add(newProduct);
                    JOptionPane.showMessageDialog(null,"Product added successfully");
                    baseProduct = newProduct;
                    var = true;
                    frmAddNewProduct.dispose();

                }
            }
        });
        btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnConfirm.setBounds(276, 227, 85, 37);
        frmAddNewProduct.getContentPane().add(btnConfirm);
        frmAddNewProduct.setVisible(true);
    }

    public Boolean getVar() {
        return var;
    }

    public BaseProduct getBaseProduct() {
        return baseProduct;
    }
}

