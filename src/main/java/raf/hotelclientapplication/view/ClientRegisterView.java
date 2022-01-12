package raf.hotelclientapplication.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientCreateDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClientRegisterView extends JDialog {
    /*
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String passportNumber;
    @NotBlank
    private String username;
    @Length(min=8,max = 30)
    private String password;
    @NotBlank
    private String phoneNumber;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthday;
     */

    private JLabel emailLabel = new JLabel("Email:");
    private JLabel firstNameLabel = new JLabel("First name:");
    private JLabel lastNameLabel = new JLabel("Last name:");
    private JLabel passportNumberLabel = new JLabel("Passport number:");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password");
    private JLabel phoneNumberLabel = new JLabel("Phone number");
    private JLabel dayLabel = new JLabel("Day");
    private JLabel monthLabel = new JLabel("Month");
    private JLabel yearLabel = new JLabel("Year");
    private JTextField usernameInput = new JTextField(20);
    private JTextField emailInput = new JTextField(20);
    private JTextField firstNameInput = new JTextField(20);
    private JTextField lastNameInput = new JTextField(20);
    private JPasswordField passwordInput = new JPasswordField(20);
    private JTextField passportInput = new JTextField(20);
    private JTextField phoneNumberInput = new JTextField(20);
    private JTextField dayInput = new JTextField(20);
    private JTextField monthInput = new JTextField(20);
    private JTextField yearInput = new JTextField(20);
    private JButton registerButton = new JButton("Register");
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private ObjectMapper objectMapper = new ObjectMapper();


    public ClientRegisterView() {
        this.setTitle("Client registration");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        registerPanel.add(firstNameLabel);
        registerPanel.add(firstNameInput);
        registerPanel.add(lastNameLabel);
        registerPanel.add(lastNameInput);
        registerPanel.add(emailLabel);
        registerPanel.add(emailInput);
        registerPanel.add(usernameLabel);
        registerPanel.add(usernameInput);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordInput);
        registerPanel.add(passportNumberLabel);
        registerPanel.add(passportInput);
        registerPanel.add(phoneNumberLabel);
        registerPanel.add(phoneNumberInput);
        registerPanel.add(dayLabel);
        registerPanel.add(dayInput);
        registerPanel.add(monthLabel);
        registerPanel.add(monthInput);
        registerPanel.add(yearLabel);
        registerPanel.add(yearInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerPanel.add(registerButton);
        registerButton.addActionListener(e -> {
            ClientCreateDto clientCreateDto = new ClientCreateDto();
            clientCreateDto.setEmail(emailInput.getText());
            clientCreateDto.setFirstName(firstNameInput.getText());
            clientCreateDto.setLastName(lastNameInput.getText());
            clientCreateDto.setPassportNumber(passportInput.getText());
            clientCreateDto.setPhoneNumber(phoneNumberInput.getText());
            clientCreateDto.setUsername(usernameInput.getText());
            clientCreateDto.setPassword(String.valueOf(passwordInput.getPassword()));
            clientCreateDto.setBirthday(LocalDate.of(Integer.parseInt(yearInput.getText()), Integer.parseInt(monthInput.getText()), Integer.parseInt(dayInput.getText())));
            DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                userServiceRestClient.registerClient(clientCreateDto);
            } catch (IOException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
            this.setVisible(false);
        });

        this.add(registerPanel);
        this.pack();
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setVisible(true);





    }
}
