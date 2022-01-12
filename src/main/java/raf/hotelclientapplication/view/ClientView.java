package raf.hotelclientapplication.view;

import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.model.NotificationTableModel;
import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientPasswordDto;
import raf.hotelclientapplication.restclient.dto.NotificationListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientView extends JDialog {

    private NotificationTableModel notificationTableModel;
    private JTable notificationTable;
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private NotificationServiceRestClient notificationServiceRestClient = new NotificationServiceRestClient();
    private JButton updateClientProfileButton = new JButton("Update profile");
    private JLabel updatePasswordLabel = new JLabel("Enter new password:");
    private JPasswordField updatePasswordField = new JPasswordField(15);
    private JButton updatePasswordButton = new JButton("Save password");
    private JLabel emailLabel = new JLabel("Enter your email:");
    private JTextField emailInput = new JTextField(20);

    ClientView() throws IOException {
        this.setTitle("Client");
        this.setVisible(true);
        setLayout(new FlowLayout());

        JPanel passwordPanel = new JPanel();

        add(updateClientProfileButton);
        passwordPanel.add(emailLabel);
        passwordPanel.add(emailInput);
        passwordPanel.add(updatePasswordLabel);
        passwordPanel.add(updatePasswordField);
        passwordPanel.add(updatePasswordButton);
        add(passwordPanel);

        updateClientProfileButton.addActionListener(e -> {
            new ClientUpdateView();
        });

        updatePasswordButton.addActionListener(e -> {
            String password = String.valueOf(updatePasswordField.getPassword());
            Long id = HotelClientApplication.getInstance().getUser().getId();
            String email = emailInput.getText();
            ClientPasswordDto clientPasswordDto = new ClientPasswordDto();
            clientPasswordDto.setEmail(email);
            clientPasswordDto.setPassword(password);
            clientPasswordDto.setId(id);
            try {
                userServiceRestClient.updatePassword(id, clientPasswordDto);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        notificationTableModel = new NotificationTableModel();
        notificationTable = new JTable(notificationTableModel);
        NotificationListDto notificationListDto = notificationServiceRestClient.getClientNotifications(HotelClientApplication.getInstance().getUser().getId());
        notificationListDto.getContent().forEach(notificationDto -> {
            notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
        });
        JScrollPane notificationTablePane = new JScrollPane();
        notificationTablePane.add(notificationTable);
        add(notificationTablePane);




        this.pack();

        //update profile
        //change password
        //get discount
        //get all clients
        //get all managers
        //list all notifications
    }
}
