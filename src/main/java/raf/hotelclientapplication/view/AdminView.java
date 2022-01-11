package raf.hotelclientapplication.view;

import raf.hotelclientapplication.model.ClientRankTableModel;
import raf.hotelclientapplication.model.ClientTableModel;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientRankListDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AdminView extends JDialog{

    /*
        private Integer minNumberOfReservations;
        private Integer maxNumberOfReservations;
        private Double discount;
     */
    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private ClientRankTableModel clientRankTableModel;
    private JTable clientRankTable;
    private JTextField banClientID = new JTextField(3);
    private JTextField unbanClientID = new JTextField(3);
    private JButton banClientButton = new JButton("Ban client");
    private JButton unbanClientButton = new JButton("Unban client");
    private JTextField banManagerID = new JTextField(3);
    private JTextField unbanManagerID = new JTextField(3);
    private JButton banManagerButton = new JButton("Ban manager");
    private JButton unbanManagerButton = new JButton("Unban manager");
    private JButton editCLientRank = new JButton("Edit client rank");
    private JPanel banUnbanPanel = new JPanel();
    private JDialog adminView;


    public AdminView() throws NoSuchMethodException, IllegalAccessException, IOException {
        adminView = this;
        this.setTitle("ADMIN");
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
//        clientTableModel = new ClientTableModel();
//        clientTable = new JTable(clientTableModel);
        banUnbanPanel.add(banClientID);
        banUnbanPanel.add(banClientButton);
        banClientButton.addActionListener(e -> {
            Long id = Long.parseLong(banClientID.getText());
            try {
                userServiceRestClient.banClient(id);
                banClientID.setText("");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        banUnbanPanel.add(unbanClientID);
        banUnbanPanel.add(unbanClientButton);
        unbanClientButton.addActionListener(e -> {
            Long id = Long.parseLong(unbanClientID.getText());
            try {
                userServiceRestClient.unbanClient(id);
                unbanClientID.setText("");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        banUnbanPanel.add(banManagerID);
        banUnbanPanel.add(banManagerButton);
        banManagerButton.addActionListener(e -> {
            Long id = Long.parseLong(banManagerID.getText());
            try {
                userServiceRestClient.banManager(id);
                banManagerID.setText("");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        banUnbanPanel.add(unbanManagerID);
        banUnbanPanel.add(unbanManagerButton);
        unbanManagerButton.addActionListener(e -> {
            Long id = Long.parseLong(unbanManagerID.getText());
            try {
                userServiceRestClient.unbanManager(id);
                unbanManagerID.setText("");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        add(banUnbanPanel);


        clientRankTableModel = new ClientRankTableModel();
        clientRankTable = new JTable(clientRankTableModel);

        ClientRankListDto clientRankListDto = userServiceRestClient.getClientRanks();
        clientRankListDto.getContent().forEach(clientRankDto -> {
            clientRankTableModel.addRow(new Object[]{clientRankDto.getName(), clientRankDto.getMinNumberOfReservations(), clientRankDto.getMaxNumberOfReservations(), clientRankDto.getDiscount()});
        });
        JScrollPane scrollPane = new JScrollPane(clientRankTable);
        add(scrollPane);
        add(editCLientRank);

        editCLientRank.addActionListener(e -> {
            String clientRankName = clientRankTableModel.getClientRankListDto().getContent().get(clientRankTable.getSelectedRow()).getName();
            new ClientRankUpdateView(clientRankName, adminView);

        });


        this.pack();



        //update client password - ZA SAD NE TREBA
        //update RANK
        //get discount
        //get all notifications
        //get notifications by email
        //get notifications between dates
        //get notification by type
        //delete notification
        //get notification types
        //update notification type
        //get client ranks
        //get clients
        //get managers
    }

}
