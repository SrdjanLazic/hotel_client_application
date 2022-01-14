package raf.hotelclientapplication.view;

import raf.hotelclientapplication.model.ClientRankTableModel;
import raf.hotelclientapplication.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ClientRankUpdateView extends JDialog {

    private JLabel clientRankName;
    private JLabel minLabel = new JLabel("Min:");
    private JTextField minInput = new JTextField(3);
    private JLabel maxLabel = new JLabel("Max:");
    private JTextField maxInput = new JTextField(3);
    private JLabel discountLabel = new JLabel("Discount:");
    private JTextField discountInput = new JTextField(3);
    private JButton updateButton = new JButton("Update");
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private AdminView parentView;

    public ClientRankUpdateView(String name, AdminView adminView){
        parentView = adminView;
        this.setTitle("Client rank update");
        this.setSize(800,600);
        clientRankName = new JLabel("Currently editing: " + name);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(clientRankName);
        mainPanel.add(Box.createRigidArea(new Dimension(30,30)));
        JPanel inputs = new JPanel();
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.add(Box.createRigidArea(new Dimension(10,0)));
        inputs.add(minLabel);
        inputs.add(Box.createRigidArea(new Dimension(10,0)));
        inputs.add(minInput);
        inputs.add(Box.createRigidArea(new Dimension(10,0)));
        inputs.add(maxLabel);
        inputs.add(Box.createRigidArea(new Dimension(10,0)));
        inputs.add(maxInput);
        inputs.add(Box.createRigidArea(new Dimension(10,0)));
        inputs.add(discountLabel);
        inputs.add(Box.createRigidArea(new Dimension(10,0)));
        inputs.add(discountInput);
        inputs.add(Box.createRigidArea(new Dimension(10,0)));
        mainPanel.add(inputs);
        mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
        mainPanel.add(updateButton);
        mainPanel.add(Box.createRigidArea(new Dimension(30,10)));
        this.add(mainPanel);
        updateButton.addActionListener(e -> {
            Double discount = Double.parseDouble(discountInput.getText());
            Integer min = Integer.parseInt(minInput.getText());
            Integer max = Integer.parseInt(maxInput.getText());
            try {
                userServiceRestClient.updateClientRank(min, max, discount, name);
                adminView.updateRankTable();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.setVisible(false);
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

}
