package presentationLayer;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import bussinesLayer.DeliveryService;
import com.toedter.calendar.JDateChooser;

public class ReportsGUI {

    private JFrame frmGenerateReports;
    private JComboBox comboEndHour;
    private JTextField tfNumberOfTimes;
    private JTextField tfNbOfTimesClients;
    private JTextField tfAmount;
    private DeliveryService deliveryService;

    public ReportsGUI(){
        initialize();
    }

    public ReportsGUI(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        initialize();
    }

    private void initialize() {
        frmGenerateReports = new JFrame();
        frmGenerateReports.getContentPane().setBackground(new Color(245, 255, 250));
        frmGenerateReports.getContentPane().setLayout(null);
        frmGenerateReports.setVisible(true);

        JCheckBox chckbxReport1 = new JCheckBox("<html>the orders performed between a given start hour and a given end hour regardless the date</html>");
        chckbxReport1.setBackground(new Color(127, 255, 212));
        chckbxReport1.setVerticalAlignment(SwingConstants.TOP);
        chckbxReport1.setHorizontalAlignment(SwingConstants.CENTER);
        chckbxReport1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chckbxReport1.setBounds(29, 78, 174, 78);
        frmGenerateReports.getContentPane().add(chckbxReport1);

        JLabel lblReports = new JLabel("Generate reports with");
        lblReports.setHorizontalAlignment(SwingConstants.CENTER);
        lblReports.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblReports.setBounds(93, 22, 345, 21);
        frmGenerateReports.getContentPane().add(lblReports);

        JCheckBox chckbxReport2 = new JCheckBox("<html>the products ordered more than a specified number of times so far</html>");
        chckbxReport2.setBackground(new Color(127, 255, 212));
        chckbxReport2.setVerticalAlignment(SwingConstants.TOP);
        chckbxReport2.setHorizontalAlignment(SwingConstants.CENTER);
        chckbxReport2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chckbxReport2.setBounds(25, 183, 178, 57);
        frmGenerateReports.getContentPane().add(chckbxReport2);

        JCheckBox chckbxReport3 = new JCheckBox("<html>the clients that have ordered more than a specified number of times and the value of the order was higher than a specified amount</html>");
        chckbxReport3.setBackground(new Color(127, 255, 212));
        chckbxReport3.setVerticalAlignment(SwingConstants.TOP);
        chckbxReport3.setHorizontalAlignment(SwingConstants.CENTER);
        chckbxReport3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chckbxReport3.setBounds(29, 269, 174, 105);
        frmGenerateReports.getContentPane().add(chckbxReport3);

        JCheckBox chckbxReport4 = new JCheckBox("<html>the products ordered within a specified day with the number of times they have been ordered</html>");
        chckbxReport4.setBackground(new Color(127, 255, 212));
        chckbxReport4.setVerticalAlignment(SwingConstants.TOP);
        chckbxReport4.setHorizontalAlignment(SwingConstants.CENTER);
        chckbxReport4.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chckbxReport4.setBounds(29, 405, 174, 78);
        frmGenerateReports.getContentPane().add(chckbxReport4);

        JLabel lblStartHour = new JLabel("Start hour");
        lblStartHour.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblStartHour.setBounds(236, 84, 78, 13);
        frmGenerateReports.getContentPane().add(lblStartHour);

        JComboBox comboStartHour = new JComboBox();
        comboStartHour.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboStartHour.setBounds(347, 81, 148, 21);
        frmGenerateReports.getContentPane().add(comboStartHour);
        comboStartHour.setModel(new DefaultComboBoxModel(new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"}));

        JLabel lblEndHour = new JLabel("End hour");
        lblEndHour.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblEndHour.setBounds(236, 121, 78, 13);
        frmGenerateReports.getContentPane().add(lblEndHour);

        comboEndHour = new JComboBox();
        comboEndHour.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboEndHour.setBounds(347, 112, 148, 21);
        frmGenerateReports.getContentPane().add(comboEndHour);
        comboEndHour.setModel(new DefaultComboBoxModel(new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"}));

        JLabel lblNumberOftimes = new JLabel("Number of times");
        lblNumberOftimes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOftimes.setBounds(236, 201, 107, 13);
        frmGenerateReports.getContentPane().add(lblNumberOftimes);

        tfNumberOfTimes = new JTextField();
        tfNumberOfTimes.setBounds(347, 199, 148, 19);
        frmGenerateReports.getContentPane().add(tfNumberOfTimes);
        tfNumberOfTimes.setColumns(10);

        JLabel lblNumberOfTimesClients = new JLabel("Number of times");
        lblNumberOfTimesClients.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfTimesClients.setBounds(236, 276, 107, 13);
        frmGenerateReports.getContentPane().add(lblNumberOfTimesClients);

        tfNbOfTimesClients = new JTextField();
        tfNbOfTimesClients.setBounds(347, 273, 148, 19);
        frmGenerateReports.getContentPane().add(tfNbOfTimesClients);
        tfNbOfTimesClients.setColumns(10);

        JLabel lblAmount = new JLabel("Amount");
        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblAmount.setBounds(236, 326, 91, 13);
        frmGenerateReports.getContentPane().add(lblAmount);

        tfAmount = new JTextField();
        tfAmount.setBounds(347, 324, 148, 19);
        frmGenerateReports.getContentPane().add(tfAmount);
        tfAmount.setColumns(10);

        JLabel lblDay = new JLabel("Day");
        lblDay.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDay.setBounds(236, 434, 59, 13);
        frmGenerateReports.getContentPane().add(lblDay);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(347, 434, 148, 19);
        frmGenerateReports.getContentPane().add(dateChooser);

        JButton btnGenerate = new JButton("Generate reports");
        btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnGenerate.setBounds(169, 503, 174, 21);
        frmGenerateReports.getContentPane().add(btnGenerate);
        btnGenerate.addActionListener(e->{
            if(chckbxReport1.isSelected()) {
                if (comboStartHour.getSelectedItem().toString().isBlank() || comboEndHour.getSelectedItem().toString().isBlank())
                    JOptionPane.showMessageDialog(null, "Select start and end hours");
                else { try {  deliveryService.generateReportTimeInterval(Integer.parseInt(comboStartHour.getSelectedItem().toString()), Integer.parseInt(comboEndHour.getSelectedItem().toString()));
                    } catch (IOException ioException) {
                        ioException.printStackTrace(); } } }
            if(chckbxReport2.isSelected()){
                if(tfNumberOfTimes.getText().isBlank())
                    JOptionPane.showMessageDialog(null,"Insert number of times ");
                else{try { deliveryService.generateReportProductsOrderedMost(Integer.parseInt(tfNumberOfTimes.getText().toString()));
                } catch (IOException ioException) {
                    ioException.printStackTrace(); } } }
            if(chckbxReport3.isSelected()){
                if(tfNbOfTimesClients.getText().isBlank()||tfAmount.getText().isBlank())
                    JOptionPane.showMessageDialog(null,"Insert number of times and amount");
                else{try { deliveryService.generateReportClients(Integer.parseInt(tfNbOfTimesClients.getText().toString()),Integer.parseInt(tfAmount.getText().toString()));
                } catch (IOException ioException) {
                    ioException.printStackTrace(); } } }
            if(chckbxReport4.isSelected()){
                if(dateChooser.getDate().toString().isBlank())
                    JOptionPane.showMessageDialog(null,"Select date");
                else{try { deliveryService.generateReportProductBasedOnDay(dateChooser.getDate());
                } catch (IOException ioException) {
                    ioException.printStackTrace(); } } }
            JOptionPane.showMessageDialog(null,"Reports generated successfully in the specific txt files");
            frmGenerateReports.dispose();
        });
        frmGenerateReports.setTitle("Generate reports");
        frmGenerateReports.setBounds(100, 100, 544, 582);
        frmGenerateReports.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


    }
}