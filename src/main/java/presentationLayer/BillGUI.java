package presentationLayer;

import bussinesLayer.DeliveryService;

import java.awt.*;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import java.util.Date;

public class BillGUI {

    private JFrame frmBill;
    private JTable table;
    private int orderNumber;
    private int totalPrice;
    private DeliveryService deliveryService;

    public BillGUI() {
        initialize();
    }

    public BillGUI(int orderNumber, int totalPrice, DeliveryService deliveryService) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.deliveryService = deliveryService;
        initialize();
    }

    private void initialize() {
        frmBill = new JFrame();
        frmBill.getContentPane().setBackground(new Color(240, 248, 255));
        frmBill.getContentPane().setLayout(null);
        frmBill.setVisible(true);

        JLabel lblOrderNumber = new JLabel("");
        lblOrderNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrderNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblOrderNumber.setBounds(77, 10, 290, 30);
        frmBill.getContentPane().add(lblOrderNumber);
        lblOrderNumber.setText("Order number "+ orderNumber);
        frmBill.setTitle("Bill");
        frmBill.setBounds(100, 100, 483, 521);
        frmBill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 85, 449, 278);
        frmBill.getContentPane().add(scrollPane);
        scrollPane.setVisible(true);
        table = new JTable();
        table.setBackground(new Color(230, 230, 250));
        table.setVisible(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setBounds(20, 57, 784, 352);
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
                new Object[50][2] ,
                new String[] {
                        "Product name","Price"}
        ));
        table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),20));
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25);
        table.getTableHeader().setAlignmentX(SwingConstants.CENTER);

        JLabel lblTotal = new JLabel("");
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTotal.setBounds(92, 381, 275, 30);
        frmBill.getContentPane().add(lblTotal);
        lblTotal.setText("Total price: "+totalPrice);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmBill.dispose();
            }
        });
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnClose.setBounds(177, 426, 96, 30);
        frmBill.getContentPane().add(btnClose);

        listProducts();

        JLabel lblDate = new JLabel("");
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDate.setBounds(77, 50, 311, 25);
        frmBill.getContentPane().add(lblDate);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = format.format( new Date() );
        lblDate.setText("Order date: "+dateString);

    }
    private void listProducts() {
        int i=0;
        for (bussinesLayer.MenuItem b: deliveryService.getMenuItems()
        ) {
            table.getModel().setValueAt(b.getItemName(),i,0);
            table.getModel().setValueAt(b.getPrice(),i,1);
            i++; }
    }
}
