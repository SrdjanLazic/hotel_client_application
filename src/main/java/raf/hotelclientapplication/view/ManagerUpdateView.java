package raf.hotelclientapplication.view;

import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientUpdateDto;
import raf.hotelclientapplication.restclient.dto.ManagerUpdateDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ManagerUpdateView  extends JDialog{

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private JLabel firstNameLabel = new JLabel("Enter new first name:");
    private JLabel lastNameLabel = new JLabel("Enter new last name:");
    private JLabel usernameLabel = new JLabel("Enter new username:");
    private JLabel emailLabel = new JLabel("Enter new email:");
    private JLabel phoneNumberLabel = new JLabel("Enter new phone number:");
    private JLabel passportNumberLabel = new JLabel("Enter new passport number:");
    private JLabel hotelLabel = new JLabel("Enter new hotel name:");
    private JTextField firstNameInput = new JTextField(15);
    private JTextField lastNameInput = new JTextField(15);
    private JTextField usernameInput = new JTextField(15);
    private JTextField emailInput = new JTextField(15);
    private JTextField phoneNumberInput = new JTextField(15);
    private JTextField passportNumberInput = new JTextField(15);
    private JTextField hotelInput = new JTextField(15);
    private JButton save = new JButton("Save");

    public ManagerUpdateView(){
        this.setTitle("Client update");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

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
        updatePanel.add(phoneNumberLabel);
        updatePanel.add(phoneNumberInput);
        updatePanel.add(hotelLabel);
        updatePanel.add(hotelInput);
        updatePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        updatePanel.add(save);


        /*
         */

        save.addActionListener(e -> {
            ManagerUpdateDto managerUpdateDto = new ManagerUpdateDto();
            managerUpdateDto.setEmail(emailInput.getText());
            managerUpdateDto.setFirstName(firstNameInput.getText());
            managerUpdateDto.setLastName(lastNameInput.getText());
            managerUpdateDto.setUsername(usernameInput.getText());
            managerUpdateDto.setPhoneNumber(phoneNumberInput.getText());
            managerUpdateDto.setHotel(hotelInput.getText());
            try {
                userServiceRestClient.updateManagerProfile(HotelClientApplication.getInstance().getUser().getId(), managerUpdateDto);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        add(updatePanel);
        this.pack();
    }
}
