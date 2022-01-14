package raf.hotelclientapplication.view;

import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientUpdateDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientUpdateView extends JDialog {

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private JLabel firstNameLabel = new JLabel("Enter new first name:");
    private JLabel lastNameLabel = new JLabel("Enter new last name:");
    private JLabel usernameLabel = new JLabel("Enter new username:");
    private JLabel emailLabel = new JLabel("Enter new email:");
    private JLabel phoneNumberLabel = new JLabel("Enter new phone number:");
    private JLabel passportNumberLabel = new JLabel("Enter new passport number:");
    private JTextField firstNameInput = new JTextField(15);
    private JTextField lastNameInput = new JTextField(15);
    private JTextField usernameInput = new JTextField(15);
    private JTextField emailInput = new JTextField(15);
    private JTextField phoneNumberInput = new JTextField(15);
    private JTextField passportNumberInput = new JTextField(15);
    private JButton save = new JButton("Save");

    public ClientUpdateView(){
        this.setTitle("Client update");

        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        updatePanel.add(firstNameLabel);
        updatePanel.add(firstNameInput);
        updatePanel.add(lastNameLabel);
        updatePanel.add(lastNameInput);
        updatePanel.add(emailLabel);
        updatePanel.add(emailInput);
        updatePanel.add(usernameLabel);
        updatePanel.add(usernameInput);
        updatePanel.add(passportNumberLabel);
        updatePanel.add(passportNumberInput);
        updatePanel.add(phoneNumberLabel);
        updatePanel.add(phoneNumberInput);
        updatePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        updatePanel.add(save);

        save.addActionListener(e -> {
            ClientUpdateDto clientUpdateDto = new ClientUpdateDto();
            clientUpdateDto.setEmail(emailInput.getText());
            clientUpdateDto.setFirstName(firstNameInput.getText());
            clientUpdateDto.setLastName(lastNameInput.getText());
            clientUpdateDto.setUsername(usernameInput.getText());
            clientUpdateDto.setPhoneNumber(phoneNumberInput.getText());
            clientUpdateDto.setPassportNumber(passportNumberInput.getText());
            try {
                userServiceRestClient.updateClientProfile(HotelClientApplication.getInstance().getUser().getId(), clientUpdateDto);
                this.setVisible(false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        add(updatePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }



}
