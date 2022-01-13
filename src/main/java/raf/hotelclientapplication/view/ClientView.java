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
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel passwordPanel = new JPanel();
//        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));

        mainPanel.add(updateClientProfileButton);
        updateClientProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(0,30)));
        passwordPanel.add(emailLabel);
        passwordPanel.add(emailInput);
        passwordPanel.add(Box.createRigidArea(new Dimension(0,20)));
        passwordPanel.add(updatePasswordLabel);
        passwordPanel.add(updatePasswordField);
        passwordPanel.add(Box.createRigidArea(new Dimension(0,10)));
        passwordPanel.add(updatePasswordButton);
        mainPanel.add(passwordPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20)));

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
                userServiceRestClient.updateClientPassword(id, clientPasswordDto);
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
//        notificationTablePane.add(notificationTable);
        notificationTablePane.setViewportView(notificationTable);
        mainPanel.add(Box.createRigidArea(new Dimension(0,30)));
        mainPanel.add(notificationTablePane);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));


        mainPanel.setVisible(true);
        this.add(mainPanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);


        //TODO:
        //get discount
        //profile information
        //pregled svih njegovih podataka
    }
}
