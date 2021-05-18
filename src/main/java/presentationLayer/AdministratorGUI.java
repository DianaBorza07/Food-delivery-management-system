package presentationLayer;

import bussinesLayer.BaseProduct;
import bussinesLayer.CompositeProduct;
import bussinesLayer.DeliveryService;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class AdministratorGUI {

    private JFrame frmAdministrator;
    private JTable table;NewProductGUI newProductGUI;
    private DeliveryService deliveryService = new DeliveryService();
    private List<CompositeProduct> compositeProducts = new ArrayList<>();
    private int idx;

    public AdministratorGUI(DeliveryService service) {
        this.deliveryService = service;
        idx=0;
        initialize();
    }

    private void initialize() {
        frmAdministrator = new JFrame();
        frmAdministrator.getContentPane().setBackground(new Color(240, 248, 255));
        frmAdministrator.setBackground(new Color(240, 255, 255));
        frmAdministrator.getContentPane().setForeground(new Color(240, 248, 255));
        frmAdministrator.setTitle("Administrator");
        frmAdministrator.setBounds(100, 100, 877, 475);
        frmAdministrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAdministrator.getContentPane().setLayout(null);
        frmAdministrator.setVisible(true);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 42, 844, 2);
        frmAdministrator.getContentPane().add(separator);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            LoginGUI log = new LoginGUI();
            frmAdministrator.dispose();
        });
        btnBack.setBounds(10, 11, 67, 21);
        frmAdministrator.getContentPane().add(btnBack);

        JButton btnGenerate = new JButton("Generate reports");
        btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnGenerate.setBounds(347, 11, 140, 21);
        frmAdministrator.getContentPane().add(btnGenerate);
        btnGenerate.addActionListener(e->{
            ReportsGUI reportsGUI = new ReportsGUI(deliveryService);
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(44, 76, 791, 352);
        frmAdministrator.getContentPane().add(scrollPane);
        scrollPane.setVisible(false);

        table = new JTable();
        table.setVisible(false);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setBounds(20, 57, 784, 352);
        scrollPane.setViewportView(table);

        table.setModel(new DefaultTableModel(
                new Object[15000][8] ,
                new String[] {
                        "ID","Title","Rating","Calories","Protein","Fat","Sodium","Price"}
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(280);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(20);
        table.getColumnModel().getColumn(5).setPreferredWidth(20);
        table.getColumnModel().getColumn(6).setPreferredWidth(20);
        table.getColumnModel().getColumn(7).setPreferredWidth(20);
        table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),20));
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setVisible(false);
        JButton btnComposite = new JButton("Add composite product");
        btnComposite.setBounds(562, 45, 189, 21);
        frmAdministrator.getContentPane().add(btnComposite);
        btnComposite.setVisible(false);
        btnComposite.addActionListener(e->{
            CompositeProduct compositeProduct = new CompositeProduct(deliveryService.getBaseProducts().size()+2,0,"Dalily menu"+idx);
            int ok=0;
            for (int row:table.getSelectedRows()
                 ) {
                int id = Integer.parseInt(table.getValueAt(row,0).toString());
                String name = table.getValueAt(row,1).toString();
                float rating = Float.parseFloat(table.getValueAt(row,2).toString());
                int calories = Integer.parseInt(table.getValueAt(row,3).toString());
                int protein = Integer.parseInt(table.getValueAt(row,4).toString());
                int fat = Integer.parseInt(table.getValueAt(row,5).toString());
                int sodium = Integer.parseInt(table.getValueAt(row,6).toString());
                int price = Integer.parseInt(table.getValueAt(row,7).toString());
                BaseProduct newProduct = new BaseProduct(id,price,name,rating,calories,protein,fat,sodium);
                compositeProduct.addItem(newProduct);
                ok=1;
            }
            if(ok==1){
                idx++;
                compositeProduct.computePrice();
                compositeProducts.add(compositeProduct);
                listProducts(deliveryService.getBaseProducts(),compositeProducts);
                JOptionPane.showMessageDialog(null,"Composite product added successfully");
            }
        });

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnAdd.setBounds(508, 11, 85, 21);
        btnAdd.setVisible(false);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 newProductGUI= new NewProductGUI(deliveryService);
                 btnRefresh.setVisible(true);
                 btnComposite.setVisible(true);
            }
        });

        btnRefresh.setBounds(767, 45, 90, 21);
        frmAdministrator.getContentPane().add(btnRefresh);
        btnRefresh.addActionListener(e->{
            if(newProductGUI.getVar())
            {deliveryService.addProduct(newProductGUI.getBaseProduct());
            listProducts(deliveryService.getBaseProducts(),compositeProducts);
            }
        });

        frmAdministrator.getContentPane().add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnDelete.setBounds(614, 11, 85, 21);
        btnDelete.setVisible(false);
        frmAdministrator.getContentPane().add(btnDelete);
        btnDelete.addActionListener(e->{
            int rowToModify = table.getSelectedRow();
            if(rowToModify != -1){
                int productID = Integer.parseInt(table.getValueAt(rowToModify,0).toString());
                deliveryService.deleteProduct(productID);
                listProducts(deliveryService.getBaseProducts(),compositeProducts);
            }
        });

        JButton btnModify = new JButton("Modify");
        btnModify.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnModify.setBounds(719, 11, 85, 21);
        btnModify.setVisible(false);
        frmAdministrator.getContentPane().add(btnModify);
        btnModify.addActionListener(e->{
            int row= table.getEditingRow();
            if(row!=-1){
                int id = Integer.parseInt(table.getValueAt(row,0).toString());
                String name = table.getValueAt(row,1).toString();
                float rating = Float.parseFloat(table.getValueAt(row,2).toString());
                int calories = Integer.parseInt(table.getValueAt(row,3).toString());
                int protein = Integer.parseInt(table.getValueAt(row,4).toString());
                int fat = Integer.parseInt(table.getValueAt(row,5).toString());
                int sodium = Integer.parseInt(table.getValueAt(row,6).toString());
                int price = Integer.parseInt(table.getValueAt(row,7).toString());
                deliveryService.modifyProduct(id,rating,name,calories,sodium,fat,protein,price);
                JOptionPane.showMessageDialog(null,"Product modified successfully");
            }
        });

        JButton btnImport = new JButton("Import products");
        btnImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAdd.setVisible(true);
                btnDelete.setVisible(true);
                btnModify.setVisible(true);
                table.setVisible(true);
                scrollPane.setVisible(true);
                try {
                    deliveryService.importProducts();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                listProducts(deliveryService.getBaseProducts(),compositeProducts);
            }
        });
        btnImport.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnImport.setBounds(193, 11, 133, 21);
        frmAdministrator.getContentPane().add(btnImport);


    }
    private void listProducts(HashSet<BaseProduct> baseProducts, List<CompositeProduct> compositeProducts) {
        int i=0;
        for (BaseProduct b: baseProducts
        ) {
            table.getModel().setValueAt(b.getItemID(),i,0);
            table.getModel().setValueAt(b.getItemName(),i,1);
            table.getModel().setValueAt(b.getRating(),i,2);
            table.getModel().setValueAt(b.getCalories(),i,3);
            table.getModel().setValueAt(b.getProtein(),i,4);
            table.getModel().setValueAt(b.getFat(),i,5);
            table.getModel().setValueAt(b.getSodium(),i,6);
            table.getModel().setValueAt(b.getPrice(),i,7);
            i++; }
        for (CompositeProduct b:compositeProducts
             ) {table.getModel().setValueAt(b.getItemID(),i,0);
            table.getModel().setValueAt(b.getItemName(),i,1);
            table.getModel().setValueAt(b.getPrice(),i,7);
            i++;

        }
    }
}

