package raf.hotelclientapplication.view;

import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ManagerDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ManagerInfoView extends JDialog {

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
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();


    public ManagerInfoView(Long id) throws IOException {
        this.setTitle("Client Info");
        this.setSize(800, 600);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

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


        this.add(registerPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);





    }

}
