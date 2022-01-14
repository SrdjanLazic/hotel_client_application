package raf.hotelclientapplication.view;

import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.model.NotificationTableModel;
import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientPasswordDto;
import raf.hotelclientapplication.restclient.dto.ManagerDto;
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

    //INFO VIEW
    private JLabel emailInfoLabel = new JLabel("Email:");
    private JLabel emailInfoInput = new JLabel();
    private JLabel firstNameLabel = new JLabel("First name:");
    private JLabel firstNameInput = new JLabel();
    private JLabel lastNameLabel = new JLabel("Last name:");
    private JLabel lastNameInput = new JLabel();
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel usernameInput = new JLabel();
    private JLabel phoneNumberLabel = new JLabel("Phone number:");
    private JLabel phoneNumberInput = new JLabel();
    private JLabel dayLabel = new JLabel("Day");
    private JLabel dayInput = new JLabel();
    private JLabel monthLabel = new JLabel("Month");
    private JLabel monthInput = new JLabel();
    private JLabel yearLabel = new JLabel("Year");
    private JLabel yearInput = new JLabel();
    private JLabel employmentDayLabel = new JLabel("Employment Day");
    private JLabel employmentDayInput = new JLabel();
    private JLabel employmentMonthLabel = new JLabel("Employment Month");
    private JLabel employmentMonthInput = new JLabel();
    private JLabel employmentYearLabel = new JLabel("Employment Year");
    private JLabel employmentYearInput = new JLabel();
    private JLabel hotelLabel = new JLabel("Hotel:");
    private JLabel hotelInput = new JLabel();

    ManagerView() throws IOException {
        this.setTitle("Manager");
        JPanel framePanel = new JPanel();
        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.X_AXIS));

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

        //INFO VIEW
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        Long id = HotelClientApplication.getInstance().getUser().getId();

        ManagerDto currentManagerDto = userServiceRestClient.findManagerById(id);
        firstNameInput.setText(currentManagerDto.getFirstName());
        lastNameInput.setText(currentManagerDto.getLastName());
        emailInfoInput.setText(currentManagerDto.getEmail());
        usernameInput.setText(currentManagerDto.getUsername());
        phoneNumberInput.setText(currentManagerDto.getPhoneNumber());
        JLabel birthday = new JLabel("Birthday:");
        JLabel birthdayInput = new JLabel();
        birthdayInput.setText(String.valueOf(currentManagerDto.getBirthday()));
        JLabel employment = new JLabel("Employment:");
        JLabel employmentInput = new JLabel();
        employmentInput.setText(String.valueOf(currentManagerDto.getEmploymentDate()));
        JLabel hotel = new JLabel("Hotel:");
        JLabel hotelInput = new JLabel();
        hotelInput.setText(String.valueOf(currentManagerDto.getHotel()));

        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerPanel.add(new JLabel("MANAGER INFO:"));
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
        registerPanel.add(employment);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        employmentInput.setOpaque(true);
        employmentInput.setBackground(Color.WHITE);
        registerPanel.add(employmentInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(hotel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        hotelInput.setOpaque(true);
        hotelInput.setBackground(Color.WHITE);
        registerPanel.add(hotelInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));


        main.setVisible(true);

        framePanel.add(main);
        framePanel.add(Box.createRigidArea(new Dimension(30,0)));
        framePanel.add(registerPanel);
        this.add(framePanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

}
