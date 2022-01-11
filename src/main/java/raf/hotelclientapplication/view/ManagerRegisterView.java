package raf.hotelclientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ManagerCreateDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManagerRegisterView extends JDialog {
        /*
     @Email
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String username;
    @Length(min=8,max = 30)
    private String password;
    @NotBlank
    private String phoneNumber;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthday;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate employmentDate;
    @NotBlank
    private String hotel;
     */

    private JLabel emailLabel = new JLabel("Email:");
    private JTextField emailInput = new JTextField(20);
    private JLabel firstNameLabel = new JLabel("First name:");
    private JTextField firstNameInput = new JTextField(20);
    private JLabel lastNameLabel = new JLabel("Last name:");
    private JTextField lastNameInput = new JTextField(20);
    private JLabel usernameLabel = new JLabel("Username:");
    private JTextField usernameInput = new JTextField(20);
    private JLabel passwordLabel = new JLabel("Password:");
    private JPasswordField passwordInput = new JPasswordField(20);
    private JLabel phoneNumberLabel = new JLabel("Phone number:");
    private JTextField phoneNumberInput = new JTextField(20);
    private JLabel dayLabel = new JLabel("Day");
    private JTextField dayInput = new JTextField(20);
    private JLabel monthLabel = new JLabel("Month");
    private JTextField monthInput = new JTextField(20);
    private JLabel yearLabel = new JLabel("Year");
    private JTextField yearInput = new JTextField(20);
    private JLabel employmentDayLabel = new JLabel("Employment Day");
    private JTextField employmentDayInput = new JTextField(20);
    private JLabel employmentMonthLabel = new JLabel("Employment Month");
    private JTextField employmentMonthInput = new JTextField(20);
    private JLabel employmentYearLabel = new JLabel("Employment Year");
    private JTextField employmentYearInput = new JTextField(20);
    private JLabel hotelLabel = new JLabel("Hotel:");
    private JTextField hotelInput = new JTextField(20);
    private JButton registerButton = new JButton("Register");
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private ObjectMapper objectMapper = new ObjectMapper();


    public ManagerRegisterView() {
        this.setTitle("Client Application");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        registerPanel.add(emailLabel);
        registerPanel.add(emailInput);
        registerPanel.add(firstNameLabel);
        registerPanel.add(firstNameInput);
        registerPanel.add(lastNameLabel);
        registerPanel.add(lastNameInput);
        registerPanel.add(usernameLabel);
        registerPanel.add(usernameInput);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordInput);
        registerPanel.add(phoneNumberLabel);
        registerPanel.add(phoneNumberInput);
        registerPanel.add(dayLabel);
        registerPanel.add(dayInput);
        registerPanel.add(monthLabel);
        registerPanel.add(monthInput);
        registerPanel.add(yearLabel);
        registerPanel.add(yearInput);
        registerPanel.add(employmentDayLabel);
        registerPanel.add(employmentDayInput);
        registerPanel.add(employmentMonthLabel);
        registerPanel.add(employmentMonthInput);
        registerPanel.add(employmentYearLabel);
        registerPanel.add(employmentYearInput);
        registerPanel.add(hotelLabel);
        registerPanel.add(hotelInput);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        registerPanel.add(registerButton);
        registerButton.addActionListener(e -> {
            ManagerCreateDto managerCreateDto = new ManagerCreateDto();
            managerCreateDto.setEmail(emailInput.getText());
            managerCreateDto.setFirstName(firstNameInput.getText());
            managerCreateDto.setLastName(lastNameInput.getText());
            managerCreateDto.setUsername(usernameInput.getText());
            managerCreateDto.setPassword(String.valueOf(passwordInput.getPassword()));
            managerCreateDto.setPhoneNumber(phoneNumberInput.getText());
            managerCreateDto.setBirthday(LocalDate.of(Integer.parseInt(yearInput.getText()), Integer.parseInt(monthInput.getText()), Integer.parseInt(dayInput.getText())));
            DateTimeFormatter.ofPattern("dd-MM-yyyy");
            managerCreateDto.setEmploymentDate(LocalDate.of(Integer.parseInt(employmentYearInput.getText()), Integer.parseInt(employmentMonthInput.getText()), Integer.parseInt(employmentDayInput.getText())));
            DateTimeFormatter.ofPattern("dd-MM-yyyy");
            managerCreateDto.setHotel(hotelInput.getText());
            try {
                userServiceRestClient.registerManager(managerCreateDto);
            } catch (IOException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
            this.setVisible(false);
        });

        this.add(registerPanel);
        this.pack();
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setVisible(true);





    }
}
