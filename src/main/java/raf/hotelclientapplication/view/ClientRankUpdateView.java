package raf.hotelclientapplication.view;

import raf.hotelclientapplication.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientRankUpdateView extends JFrame {

    private JLabel clientRankName;
    private JLabel minLabel = new JLabel("Min:");
    private JTextField minInput = new JTextField(3);
    private JLabel maxLabel = new JLabel("Max:");
    private JTextField maxInput = new JTextField(3);
    private JLabel discountLabel = new JLabel("Discount:");
    private JTextField discountInput = new JTextField(3);
    private JButton updateButton = new JButton("Update");
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();

    public ClientRankUpdateView(String name){
        this.setTitle("Client rank update");
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        clientRankName = new JLabel("Currently editing: " + name);
        add(clientRankName);
        add(minLabel);
        add(minInput);
        add(maxLabel);
        add(maxInput);
        add(discountLabel);
        add(discountInput);
        add(updateButton);
        updateButton.addActionListener(e -> {
            Double discount = Double.parseDouble(discountInput.getText());
            Integer min = Integer.parseInt(minInput.getText());
            Integer max = Integer.parseInt(maxInput.getText());
            try {
                userServiceRestClient.updateClientRank(min, max, discount, name);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        this.pack();

    }

}
