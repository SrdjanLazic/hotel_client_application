package raf.hotelclientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.model.User;
import raf.hotelclientapplication.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Base64;

public class HomeView extends JPanel {

    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel rolePanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JButton clientRegister;
    private JButton managerRegister;
    private JRadioButton adminButton;
    private JRadioButton clientButton;
    private JRadioButton managerButton;
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public HomeView(){
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));

        usernameLabel = new JLabel("Email: ");
        passwordLabel = new JLabel("Password: ");
        usernameInput = new JTextField(10);
        passwordInput = new JPasswordField(10);
        loginButton = new JButton("Login");
        clientRegister = new JButton("Register client");
        managerRegister = new JButton("Register manager");

        //INPUT PANEL
        rolePanel = new JPanel();
        adminButton = new JRadioButton("Admin");
        adminButton.setSelected(true);
        adminButton.setActionCommand("admin");
        managerButton = new JRadioButton("Manager");
        managerButton.setActionCommand("manager");
        clientButton = new JRadioButton("Client");
        clientButton.setActionCommand("client");
        ButtonGroup group = new ButtonGroup();
        group.add(adminButton);
        group.add(managerButton);
        group.add(clientButton);
        rolePanel.add(adminButton);
        rolePanel.add(clientButton);
        rolePanel.add(managerButton);
        this.add(rolePanel);

        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 50, 30));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameInput);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordInput);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputPanel.add(loginButton);
        loginButton.addActionListener(e -> {
            String role = group.getSelection().getActionCommand();
            String email = usernameInput.getText();
            String password = String.valueOf(passwordInput.getPassword());
            try {
                String token = userServiceRestClient.login(email, password, role);
                System.out.println(token);
                String userAsJSON = new String(Base64.getDecoder().decode(token.split("\\.")[1]));
                User user = objectMapper.readValue(userAsJSON, User.class);
                HotelClientApplication.getInstance().setUser(user);
                HotelClientApplication.getInstance().setToken(token);

                if(role.equalsIgnoreCase("admin")){
                    new AdminView();
                } else if(role.equalsIgnoreCase("client")){
                    new ClientView();
                    System.out.println(user.getId());
                    new ClientInfoView(user.getId());
                } else if(role.equalsIgnoreCase("manager")){
                    new ManagerView();
                    new ManagerInfoView(user.getId());
                }

            } catch (IOException | IllegalAccessException | NoSuchMethodException ioException) {
                ioException.printStackTrace();
            } catch (RuntimeException runtimeException){
                JOptionPane.showMessageDialog(this,
                        "Error logging in.",
                        "Login error",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
        this.add(inputPanel);

        //BUTTON PANEL
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(clientRegister);
        clientRegister.setAlignmentX(CENTER_ALIGNMENT);
        clientRegister.addActionListener(e -> {
            new ClientRegisterView();
        });
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPanel.add(managerRegister);
        managerRegister.addActionListener( e -> {
            new ManagerRegisterView();
        });
        managerRegister.setAlignmentX(CENTER_ALIGNMENT);
        this.add(buttonPanel);
    }
}
