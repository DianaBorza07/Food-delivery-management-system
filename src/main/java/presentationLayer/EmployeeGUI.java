package presentationLayer;

import bussinesLayer.DeliveryService;
import bussinesLayer.MenuItem;
import bussinesLayer.Order;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import javax.swing.JScrollPane;
import javax.swing.JButton;

public class EmployeeGUI implements Observer{

    private JFrame frmEmployee;
    private JTextArea taDisplay;
    private DeliveryService deliveryService;
    private boolean fromClient;
    private JButton btnPerformOrder;
    private JButton btnBack;

    public EmployeeGUI() {
        fromClient = true;
        initialize();
    }
    public EmployeeGUI(DeliveryService deliveryService) {
        fromClient = false;
        this.deliveryService = deliveryService;
        initialize();
    }
    private void initialize() {
        frmEmployee = new JFrame();
        frmEmployee.getContentPane().setBackground(new Color(216, 191, 216));
        frmEmployee.getContentPane().setLayout(null);
        frmEmployee.setVisible(true);

        JLabel lblTitle = new JLabel("Orders");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblTitle.setBounds(180, 10, 127, 23);
        frmEmployee.getContentPane().add(lblTitle);

        taDisplay = new JTextArea();
        taDisplay.setBackground(new Color(230, 230, 250));
        taDisplay.setBounds(27, 61, 444, 439);
        taDisplay.setEditable(false);
        taDisplay.setLineWrap(true);
        taDisplay.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(taDisplay);
        scrollPane.setBounds(20, 40, 454, 477);
        frmEmployee.getContentPane().add(scrollPane);

        btnBack = new JButton("Back");
        btnBack.addActionListener(e->{
            LoginGUI loginGUI = new LoginGUI();
            frmEmployee.dispose();
        });
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnBack.setBounds(20, 15, 85, 21);
        frmEmployee.getContentPane().add(btnBack);

        frmEmployee.setTitle("Employee");
        frmEmployee.setBounds(100, 100, 512, 564);
        frmEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(!fromClient)
            writeOrders();

        btnPerformOrder = new JButton("Prepare order");
        btnPerformOrder.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnPerformOrder.setBounds(328, 15, 146, 21);
        btnPerformOrder.addActionListener(e->{
            deliveryService.prepareOrder();
            taDisplay.setText(" ");
            writeOrders();
            deliveryService.saveInformation();
        });
        frmEmployee.getContentPane().add(btnPerformOrder);

    }

    @Override
    public void update(Order order, HashSet<MenuItem> menuItems) {
        String message = "NEW ORDER!\n";
        taDisplay.append(message);
        writeOrder(order,menuItems);
    }

    private void writeOrders(){
        deliveryService.getOrderListMap().forEach((order,items)->{
            taDisplay.append(order.toString()+"\n");
            taDisplay.append("Products:\n");
            items.iterator().forEachRemaining(e->taDisplay.append(e.toString()));
            taDisplay.append("\n");
        });
    }
    private void writeOrder(Order order, HashSet<MenuItem> menuItems){
        taDisplay.append(order.toString()+"\n");
        taDisplay.append("Products:\n");
        for (MenuItem m: menuItems
             ) {
            taDisplay.append(m.toString());
        }
        taDisplay.append("\n\n");
    }

    public void setButtonInvisible(){
        btnPerformOrder.setVisible(false);
    }

    public void setFrameInvisible(){
        frmEmployee.setVisible(false);
    }

    public void setFrameVisible(){
        frmEmployee.setVisible(true);
    }

    public void disposeFrame(){
        frmEmployee.dispose();
    }

    public void setBtnBackInvisible(){
        btnBack.setVisible(false);
    }

}

