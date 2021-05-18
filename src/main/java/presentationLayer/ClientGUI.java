package presentationLayer;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import bussinesLayer.BaseProduct;
import bussinesLayer.DeliveryService;
import bussinesLayer.Order;
import dataLayer.Serializator;

import java.util.List;

public class ClientGUI {

    private JFrame frmClient;
    private JTable table;
    DeliveryService deliveryService = new DeliveryService();
    private JTextField tfMax;
    private JTextField tfMin;
    private int clientID;
    private  EmployeeGUI employee;

    public ClientGUI(){
        initialize();
    }
    public ClientGUI(DeliveryService deliveryService,int clientID) {
        this.clientID = clientID;
        this.deliveryService = deliveryService;
        initialize();
    }
    private void initialize() {
        frmClient = new JFrame();
        frmClient.setTitle("Client");
        frmClient.setVisible(true);
        frmClient.getContentPane().setBackground(new Color(240, 128, 128));
        frmClient.setBounds(100, 100, 877, 602);
        frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmClient.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                deliveryService.saveInformation();
                e.getWindow().dispose();
            }
        });
        frmClient.setBounds(100, 100, 877, 570);
        frmClient.getContentPane().setLayout(null);
        JSeparator separator = new JSeparator();
        separator.setBounds(10, 42, 844, 2);
        frmClient.getContentPane().add(separator);
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            LoginGUI log = new LoginGUI();
            //deliveryService.saveInformation();
            employee.disposeFrame();
            frmClient.dispose(); });
        btnBack.setBounds(10, 11, 67, 21);
        frmClient.getContentPane().add(btnBack);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(41, 178, 791, 345);
        frmClient.getContentPane().add(scrollPane);
        scrollPane.setVisible(true);
        table = new JTable();
        table.setBackground(new Color(255, 228, 225));
        table.setVisible(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setBounds(20, 57, 784, 352);
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[15000][8] ,
                new String[] {
                        "ID","Title", "Rating","Calories","Protein","Fat","Sodium","Price"}
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
        try {
            deliveryService.importProducts();
        } catch (IOException e) {
            e.printStackTrace(); }
        listProducts(deliveryService.getBaseProducts());
        JButton btnConfirm = new JButton("Confirm order");
        btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnConfirm.setBounds(629, 11, 155, 21);
        frmClient.getContentPane().add(btnConfirm);
        JLabel lblSearch = new JLabel("<html>Depending on what criteria do you want to search for products?</html>");
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSearch.setBounds(46, 50, 164, 73);
        lblSearch.setVisible(false);
        frmClient.getContentPane().add(lblSearch);
        JRadioButton rdbtnRating = new JRadioButton("Rating");
        rdbtnRating.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnRating.setBounds(227, 50, 103, 21);
        rdbtnRating.setVisible(false);
        frmClient.getContentPane().add(rdbtnRating);
        JRadioButton rdbtnCalories = new JRadioButton("Calories");
        rdbtnCalories.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnCalories.setBounds(227, 70, 103, 21);
        rdbtnCalories.setVisible(false);
        frmClient.getContentPane().add(rdbtnCalories);
        tfMax = new JTextField();
        tfMax.setBounds(527, 54, 210, 19);
        tfMax.setVisible(false);
        frmClient.getContentPane().add(tfMax);
        tfMax.setColumns(10);
        tfMin = new JTextField();
        tfMin.setBounds(527, 92, 210, 19);
        tfMin.setVisible(false);
        frmClient.getContentPane().add(tfMin);
        tfMin.setColumns(10);
        JLabel lblMaximum = new JLabel("Maximum value");
        lblMaximum.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMaximum.setBounds(378, 54, 126, 13);
        lblMaximum.setVisible(false);
        frmClient.getContentPane().add(lblMaximum);
        JLabel lblMinimum = new JLabel("Minimum value");
        lblMinimum.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMinimum.setBounds(378, 94, 126, 13);
        lblMinimum.setVisible(false);
        frmClient.getContentPane().add(lblMinimum);
        JRadioButton rdbtnProteins = new JRadioButton("Proteins");
        rdbtnProteins.setVisible(false);
        rdbtnProteins.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnProteins.setBounds(227, 90, 103, 21);
        frmClient.getContentPane().add(rdbtnProteins);
        JRadioButton rdbtnFats = new JRadioButton("Fats");
        rdbtnFats.setVisible(false);
        rdbtnFats.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnFats.setBounds(227, 111, 103, 21);
        frmClient.getContentPane().add(rdbtnFats);
        JRadioButton rdbtnSodium = new JRadioButton("Sodium");
        rdbtnSodium.setVisible(false);
        rdbtnSodium.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnSodium.setBounds(227, 130, 103, 21);
        frmClient.getContentPane().add(rdbtnSodium);
        JRadioButton rdbtnPrice = new JRadioButton("Price");
        rdbtnPrice.setVisible(false);
        rdbtnPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        rdbtnPrice.setBounds(227, 151, 103, 21);
        frmClient.getContentPane().add(rdbtnPrice);
        JButton btnConfirmSelection = new JButton("Confirm");
        btnConfirmSelection.addActionListener(e -> {
            if(!rdbtnRating.isSelected() && !rdbtnCalories.isSelected())
                JOptionPane.showMessageDialog(null,"Select one criteria for searching the products");
            else
            if(tfMax.getText().isBlank() || tfMin.getText().isBlank())
                JOptionPane.showMessageDialog(null,"Insert the minimum and the maximum values");
            else {
                HashSet<BaseProduct> baseProducts = new HashSet<>();
                if(rdbtnRating.isSelected()) {
                    float maxValue = Float.parseFloat(tfMax.getText());
                    float minValue = Float.parseFloat(tfMin.getText());
                    baseProducts = deliveryService.getProductsBasedOnRating(minValue, maxValue); }
                else {
                    int maxValue = Integer.parseInt(tfMax.getText());
                    int minValue = Integer.parseInt(tfMin.getText());
                    if (rdbtnCalories.isSelected()) baseProducts = deliveryService.getProductsBasedOnCalories(minValue, maxValue);
                    if(rdbtnFats.isSelected()) baseProducts = deliveryService.getProductsBasedOnFats(minValue,maxValue);
                    if(rdbtnProteins.isSelected()) baseProducts = deliveryService.getProductsBasedOnProteins(minValue,maxValue);
                    if(rdbtnSodium.isSelected()) baseProducts = deliveryService.getProductsBasedOnSodium(minValue,maxValue);
                    if(rdbtnPrice.isSelected()) baseProducts = deliveryService.getProductsBasedOnPrice(minValue,maxValue); }
                listProducts(baseProducts); } });
        btnConfirmSelection.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnConfirmSelection.setBounds(747, 70, 85, 21);
        btnConfirmSelection.setVisible(false);
        frmClient.getContentPane().add(btnConfirmSelection);
        JButton btnSearch = new JButton("Search product");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnSearch.setBounds(359, 11, 155, 21);
        frmClient.getContentPane().add(btnSearch);
        rdbtnRating.addActionListener(e -> {
                rdbtnCalories.setSelected(false);rdbtnPrice.setSelected(false);
                rdbtnFats.setSelected(false);rdbtnProteins.setSelected(false);rdbtnSodium.setSelected(false); });
        rdbtnCalories.addActionListener(e -> {
                rdbtnRating.setSelected(false);rdbtnPrice.setSelected(false);rdbtnFats.setSelected(false);
                rdbtnProteins.setSelected(false);rdbtnSodium.setSelected(false); });
        rdbtnProteins.addActionListener(e -> {
            rdbtnRating.setSelected(false);rdbtnPrice.setSelected(false);rdbtnFats.setSelected(false);
            rdbtnSodium.setSelected(false);rdbtnCalories.setSelected(false); });
        rdbtnFats.addActionListener(e -> {
            rdbtnRating.setSelected(false);rdbtnPrice.setSelected(false);rdbtnProteins.setSelected(false);
            rdbtnSodium.setSelected(false);rdbtnCalories.setSelected(false); });
        rdbtnSodium.addActionListener(e -> {
            rdbtnRating.setSelected(false);rdbtnPrice.setSelected(false);rdbtnFats.setSelected(false);
            rdbtnProteins.setSelected(false);rdbtnCalories.setSelected(false); });
        rdbtnPrice.addActionListener(e -> {
            rdbtnRating.setSelected(false);rdbtnProteins.setSelected(false);rdbtnFats.setSelected(false);
            rdbtnSodium.setSelected(false);rdbtnCalories.setSelected(false); });
        btnSearch.addActionListener(e -> { employee.setFrameInvisible();
            lblSearch.setVisible(true);rdbtnRating.setVisible(true);rdbtnCalories.setVisible(true);
            tfMax.setVisible(true);tfMin.setVisible(true);lblMaximum.setVisible(true);
            lblMinimum.setVisible(true);btnConfirmSelection.setVisible(true);rdbtnSodium.setVisible(true);
            rdbtnFats.setVisible(true);rdbtnPrice.setVisible(true);rdbtnProteins.setVisible(true); });
        this.employee = new EmployeeGUI();
        employee.setButtonInvisible();
        employee.setFrameInvisible();
        employee.setBtnBackInvisible();
        btnConfirm.addActionListener(e->{
            if(table.getSelectedRows().length ==0)
                JOptionPane.showMessageDialog(null,"Select minimum one product from the list");
            else{
                List<Integer> id = new ArrayList<Integer>();
                for (int row : table.getSelectedRows()
                     ) { id.add(Integer.parseInt(table.getValueAt(row,0).toString()));
                }
                deliveryService.addMenuItems(id);
                Order order = deliveryService.addOrder(deliveryService.getMenuItems(),clientID);
                BillGUI bill = new BillGUI(order.getOrderID(),deliveryService.computePrice(),deliveryService);
                employee.setFrameVisible();
                employee.update(order,deliveryService.getMenuItems());
                deliveryService.getMenuItems().clear();
                deliveryService.saveInformation();
            }
        });
    }
    private void listProducts(HashSet<BaseProduct> baseProducts) {
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
    }
}
