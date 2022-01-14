package raf.hotelclientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientCreateDto;
import raf.hotelclientapplication.restclient.dto.ClientDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientInfoView extends JDialog {

    private JLabel emailLabel = new JLabel("Email:");
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
    private JLabel emailInput = new JLabel();
    private JLabel firstNameInput = new JLabel();
    private JLabel lastNameInput = new JLabel();
    private JLabel passwordInput = new JLabel();
    private JLabel passportInput = new JLabel();
    private JLabel phoneNumberInput = new JLabel();
    private JLabel dayInput = new JLabel();
    private JLabel monthInput = new JLabel();
    private JLabel yearInput = new JLabel();
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private ObjectMapper objectMapper = new ObjectMapper();


    public ClientInfoView(Long id) throws IOException {
        this.setTitle("Client Info");
        this.setSize(800, 600);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        ClientDto currentClientDto = userServiceRestClient.findById(id);
        firstNameInput.setText(currentClientDto.getFirstName());
        lastNameInput.setText(currentClientDto.getLastName());
        emailInput.setText(currentClientDto.getEmail());
        usernameInput.setText(currentClientDto.getUsername());
        passportInput.setText(currentClientDto.getPassportNumber());
        phoneNumberInput.setText(currentClientDto.getPhoneNumber());
        JLabel birthday = new JLabel("Birthday:");
        JLabel birthdayInput = new JLabel();
        birthdayInput.setText(String.valueOf(currentClientDto.getBirthday()));
        JLabel reservations = new JLabel("Number of Reservations:");
        JLabel reservationsInput = new JLabel();
        reservationsInput.setText(String.valueOf(currentClientDto.getNumberOfReservations()));

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
        registerPanel.add(emailLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        emailInput.setOpaque(true);
        emailInput.setBackground(Color.WHITE);
        registerPanel.add(emailInput);
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


        this.add(registerPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);





    }

}
