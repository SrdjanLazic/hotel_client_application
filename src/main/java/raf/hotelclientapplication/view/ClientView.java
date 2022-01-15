package raf.hotelclientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.model.NotificationTableModel;
import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientDto;
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
    //INFO VIEW
    private JLabel emailInfoLabel = new JLabel("Email:");
    private JLabel firstNameLabel = new JLabel("First name:");
    private JLabel lastNameLabel = new JLabel("Last name:");
    private JLabel passportNumberLabel = new JLabel("Passport number:");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password: (min 8 chars)");
    private JLabel phoneNumberLabel = new JLabel("Phone number:");
    private JLabel dayLabel = new JLabel("Day");
    private JLabel monthLabel = new JLabel("Month");
    private JLabel yearLabel = new JLabel("Year");
    private JLabel usernameInput = new JLabel();
    private JLabel emailInfoInput = new JLabel();
    private JLabel firstNameInput = new JLabel();
    private JLabel lastNameInput = new JLabel();
    private JLabel passwordInput = new JLabel();
    private JLabel passportInput = new JLabel();
    private JLabel phoneNumberInput = new JLabel();
    private JLabel dayInput = new JLabel();
    private JLabel monthInput = new JLabel();
    private JLabel yearInput = new JLabel();
    JLabel birthdayInput = new JLabel();

    ClientView() throws IOException {
        this.setTitle("Client");
        JPanel framePanel = new JPanel();
        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.X_AXIS));

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
            new ClientUpdateView(this);
        });

        updatePasswordButton.addActionListener(e -> {
            String password = String.valueOf(updatePasswordField.getPassword());
            Long id = HotelClientApplication.getInstance().getUser().getId();
            String email = emailInput.getText();
            ClientPasswordDto clientPasswordDto = new ClientPasswordDto();
            clientPasswordDto.setEmail(email);
            clientPasswordDto.setPassword(password);
            clientPasswordDto.setId(id);
            emailInput.setText("");
            updatePasswordField.setText("");
            JOptionPane.showMessageDialog(this,
                    "To confirm your password change please check your email.",
                    "Password change",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                userServiceRestClient.updateClientPassword(id, clientPasswordDto);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this,
                        "Could not change password please try again.",
                        "Error changing password",
                        JOptionPane.ERROR_MESSAGE);
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

        //INFO VIEW

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        Long id = HotelClientApplication.getInstance().getUser().getId();

        ClientDto currentClientDto = userServiceRestClient.findById(id);
        firstNameInput.setText(currentClientDto.getFirstName());
        lastNameInput.setText(currentClientDto.getLastName());
        emailInfoInput.setText(currentClientDto.getEmail());
        usernameInput.setText(currentClientDto.getUsername());
        passportInput.setText(currentClientDto.getPassportNumber());
        phoneNumberInput.setText(currentClientDto.getPhoneNumber());
        JLabel birthday = new JLabel("Birthday:");
        birthdayInput.setText(String.valueOf(currentClientDto.getBirthday()));
        JLabel reservations = new JLabel("Number of Reservations:");
        JLabel reservationsInput = new JLabel();
        reservationsInput.setText(String.valueOf(currentClientDto.getNumberOfReservations()));

        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerPanel.add(new JLabel("CLIENT INFO:"));
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerPanel.add(firstNameLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        firstNameInput.setOpaque(true);
        firstNameInput.setBackground(Color.WHITE);
        registerPanel.add(firstNameInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(lastNameLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        lastNameInput.setOpaque(true);
        lastNameInput.setBackground(Color.WHITE);
        registerPanel.add(lastNameInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(emailInfoLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        emailInfoInput.setOpaque(true);
        emailInfoInput.setBackground(Color.WHITE);
        registerPanel.add(emailInfoInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(usernameLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        usernameInput.setOpaque(true);
        usernameInput.setBackground(Color.WHITE);
        registerPanel.add(usernameInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(passportNumberLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        passportInput.setOpaque(true);
        passportInput.setBackground(Color.WHITE);
        registerPanel.add(passportInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(phoneNumberLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        phoneNumberInput.setOpaque(true);
        phoneNumberInput.setBackground(Color.WHITE);
        registerPanel.add(phoneNumberInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(birthday);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        birthdayInput.setOpaque(true);
        birthdayInput.setBackground(Color.WHITE);
        registerPanel.add(birthdayInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(reservations);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        reservationsInput.setOpaque(true);
        reservationsInput.setBackground(Color.WHITE);
        registerPanel.add(reservationsInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton discountButton = new JButton("Get Your Discount");
        discountButton.addActionListener( e-> {
            try {
                new DiscountWindow(currentClientDto.getId());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        registerPanel.add(discountButton);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        framePanel.add(mainPanel);
        framePanel.add(Box.createRigidArea(new Dimension(30,0)));
        framePanel.add(registerPanel);

        this.add(framePanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);

    }

    public void updateClientInfo() throws IOException {
        Long id = HotelClientApplication.getInstance().getUser().getId();
        ClientDto clientDto = userServiceRestClient.findById(id);

        firstNameInput.setText(clientDto.getFirstName());
        lastNameInput.setText(clientDto.getLastName());
        emailInfoInput.setText(clientDto.getEmail());
        usernameInput.setText(clientDto.getUsername());
        passportInput.setText(clientDto.getPassportNumber());
        phoneNumberInput.setText(clientDto.getPhoneNumber());
        birthdayInput.setText(String.valueOf(clientDto.getBirthday()));
    }
}
