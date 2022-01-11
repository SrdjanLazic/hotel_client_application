package raf.hotelclientapplication.view;

import raf.hotelclientapplication.model.ClientRankTableModel;
import raf.hotelclientapplication.model.NotificationTableModel;
import raf.hotelclientapplication.model.NotificationTypeTableModel;
import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientRankListDto;
import raf.hotelclientapplication.restclient.dto.NotificationListDto;
import raf.hotelclientapplication.restclient.dto.NotificationTypeListDto;

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
    private NotificationServiceRestClient notificationServiceRestClient = new NotificationServiceRestClient();
    private NotificationTypeTableModel notificationTypeTableModel;
    private JTable clientRankTable;
    private JTable notificationTypeTable;
    private JTable notificationTable;
    private NotificationTableModel notificationTableModel;
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
    private JButton deleteNotificationTypeButton = new JButton("Delete notification type");
    private JButton updateNotificationTypeButton = new JButton("Update notification type");
    private JDialog adminView;
    private JTextField filterByEmailInput = new JTextField(15);
    private JButton filterByEmailButton = new JButton("Filter by email");
    private JTextField filterByTypeInput = new JTextField(15);
    private JButton filterByTypeButton = new JButton("Filter by type");
    private JTextField filterByDate1 = new JTextField(15);
    private JTextField filterByDate2 = new JTextField(15);
    private JButton filterByDatesButton = new JButton("Filter by dates");
    private JButton resetFilterButton = new JButton("Reset filter");

    public AdminView() throws NoSuchMethodException, IllegalAccessException, IOException {
        adminView = this;
        this.setTitle("ADMIN");
        this.setSize(800,600);
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

        notificationTypeTableModel = new NotificationTypeTableModel();
        notificationTypeTable = new JTable(notificationTypeTableModel);

        NotificationTypeListDto notificationTypeListDto = notificationServiceRestClient.getAllNotificationTypes();
        notificationTypeListDto.getContent().forEach(notificationTypeDto -> {
            notificationTypeTableModel.addRow(new Object[]{notificationTypeDto.getType(), notificationTypeDto.getMessage(), notificationTypeDto.getId()});
        });

        JScrollPane scrollPaneNotificationTypes = new JScrollPane(notificationTypeTable);
        add(scrollPaneNotificationTypes);

        add(deleteNotificationTypeButton);

        deleteNotificationTypeButton.addActionListener(e -> {
            Long id = notificationTypeTableModel.getNotificationTypeListDto().getContent().get(notificationTypeTable.getSelectedRow()).getId();
            try {
                notificationServiceRestClient.deleteNotificationType(id);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        add(updateNotificationTypeButton);

        updateNotificationTypeButton.addActionListener( e -> {
            Long id = notificationTypeTableModel.getNotificationTypeListDto().getContent().get(notificationTypeTable.getSelectedRow()).getId();
            new NotificationTypeUpdateView(id, adminView);
        });


        notificationTableModel = new NotificationTableModel();
        notificationTable = new JTable(notificationTableModel);

        NotificationListDto notificationListDto = notificationServiceRestClient.getAllNotifications();
        notificationListDto.getContent().forEach(notificationDto -> {
            notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
        });

        JScrollPane notificationsScrollPanel = new JScrollPane(notificationTable);
        add(notificationsScrollPanel);

        add(filterByEmailInput);
        add(filterByEmailButton);
        filterByEmailButton.addActionListener(e -> {
            String email = filterByEmailInput.getText();
            try {
                NotificationListDto filteredNotifications = notificationServiceRestClient.getNotificationsByEmail(email);
                notificationTableModel.setRowCount(0);
                filteredNotifications.getContent().forEach(notificationDto -> {
                    notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
                });

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        add(filterByTypeInput);
        add(filterByTypeButton);
        filterByTypeButton.addActionListener(e -> {
            String type = filterByTypeInput.getText();
            try {
                NotificationListDto filteredNotifications = notificationServiceRestClient.getNotificationsByType(type);
                notificationTableModel.setRowCount(0);
                filteredNotifications.getContent().forEach(notificationDto -> {
                    notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
                });

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        add(filterByDate1);
        add(filterByDate2);
        add(filterByDatesButton);
        filterByDatesButton.addActionListener(e -> {
            String date1 = filterByDate1.getText();
            String date2 = filterByDate2.getText();
            try {
                NotificationListDto filteredNotifications = notificationServiceRestClient.getNotificationsByDate(date1, date2);
                notificationTableModel.setRowCount(0);
                filteredNotifications.getContent().forEach(notificationDto -> {
                    notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
                });

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        add(resetFilterButton);
        resetFilterButton.addActionListener(e -> {
            notificationTableModel.setRowCount(0);
            notificationListDto.getContent().forEach(notificationDto -> {
                notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
            });
        });


        this.pack();



        // TODO:
        //filter notifications between dates
        //filter notification by type
    }

}
