package raf.hotelclientapplication.view;

import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.model.NotificationTableModel;
import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientPasswordDto;
import raf.hotelclientapplication.restclient.dto.ManagerPasswordDto;
import raf.hotelclientapplication.restclient.dto.NotificationListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ManagerView extends JDialog{

    private NotificationTableModel notificationTableModel;
    private JTable notificationTable;
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private NotificationServiceRestClient notificationServiceRestClient = new NotificationServiceRestClient();

    private JButton updateManagerProfileButton = new JButton("Update profile");
    private JLabel updatePasswordLabel = new JLabel("Enter new password:");
    private JPasswordField updatePasswordField = new JPasswordField(15);
    private JButton updatePasswordButton = new JButton("Save password");
    private JLabel emailLabel = new JLabel("Enter your email:");
    private JTextField emailInput = new JTextField(20);

    ManagerView() throws IOException {
        this.setTitle("Manager");

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel passwordPanel = new JPanel();

        main.add(updateManagerProfileButton);
        updateManagerProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(Box.createRigidArea(new Dimension(0,30)));
        passwordPanel.add(emailLabel);
        passwordPanel.add(emailInput);
        passwordPanel.add(Box.createRigidArea(new Dimension(0,20)));
        passwordPanel.add(updatePasswordLabel);
        passwordPanel.add(updatePasswordField);
        passwordPanel.add(updatePasswordButton);
        passwordPanel.add(Box.createRigidArea(new Dimension(0,10)));
        main.add(passwordPanel);
        main.add(Box.createRigidArea(new Dimension(0,20)));

        updateManagerProfileButton.addActionListener(e -> {
            new ManagerUpdateView();
        });

        updatePasswordButton.addActionListener(e -> {
            String password = String.valueOf(updatePasswordField.getPassword());
            Long id = HotelClientApplication.getInstance().getUser().getId();
            String email = emailInput.getText();
            ManagerPasswordDto managerPasswordDto = new ManagerPasswordDto();
            managerPasswordDto.setEmail(email);
            managerPasswordDto.setPassword(password);
            managerPasswordDto.setId(id);
            emailInput.setText("");
            updatePasswordField.setText("");
            JOptionPane.showMessageDialog(this,
                    "To confirm your password change please check your email.",
                    "Password change",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                userServiceRestClient.updateManagerPassword(id, managerPasswordDto);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this,
                        "Could not change password please try again.",
                        "Error changing password",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        notificationTableModel = new NotificationTableModel();
        notificationTable = new JTable(notificationTableModel);
        NotificationListDto notificationListDto = notificationServiceRestClient.getManagerNotifications(HotelClientApplication.getInstance().getUser().getId());
        notificationListDto.getContent().forEach(notificationDto -> {
            notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
        });
        JScrollPane notificationTablePane = new JScrollPane();
        notificationTablePane.setViewportView(notificationTable);
        main.add(Box.createRigidArea(new Dimension(0,30)));
        main.add(notificationTablePane);
        main.add(Box.createRigidArea(new Dimension(0,10)));


        main.setVisible(true);
        this.add(main);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    //TODO:
    //get discount
    //profile information
    //pregled svih njegovih podataka
}
