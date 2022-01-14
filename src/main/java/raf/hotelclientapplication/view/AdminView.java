package raf.hotelclientapplication.view;

import raf.hotelclientapplication.model.*;
import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    private ClientTableModel clientTableModel;
    private ManagerTableModel managerTableModel;
    private JTable clientTable;
    private JTable managerTable;
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

//        this.setLayout(new FlowLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
//        this.add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        //BANUNBAN TAB

        banUnbanPanel.setLayout(new BoxLayout(banUnbanPanel, BoxLayout.X_AXIS));

        JPanel clientSide = new JPanel();
        clientSide.setLayout(new BoxLayout(clientSide, BoxLayout.Y_AXIS));


        clientTableModel = new ClientTableModel();
        clientTable = new JTable(clientTableModel);
        ClientListDto clientListDto = userServiceRestClient.getClients();
        clientListDto.getContent().forEach(clientDto -> {
            System.out.println(clientDto);
            clientTableModel.addRow(new Object[]{clientDto.getEmail(), clientDto.isAccess(), clientDto.getId()});
        });
        JScrollPane clientTablePane = new JScrollPane();
        clientTablePane.setViewportView(clientTable);
        clientSide.add(clientTablePane);
        clientSide.add(Box.createRigidArea(new Dimension(0, 10)));
        clientSide.add(banClientButton);
        banClientButton.addActionListener(e -> {
            Long id = clientTableModel.getClientListDto().getContent().get(clientTable.getSelectedRow()).getId();
            System.out.println(id);
            try {
                userServiceRestClient.banClient(id);
                clientTableModel.setRowCount(0);
                userServiceRestClient.getClients().getContent().forEach(clientDto -> {
                    clientTableModel.addRow(new Object[]{clientDto.getEmail(), clientDto.isAccess(), clientDto.getId()});
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        clientSide.add(Box.createRigidArea(new Dimension(0, 10)));
        clientSide.add(unbanClientButton);
        unbanClientButton.addActionListener(e -> {
            Long id = clientTableModel.getClientListDto().getContent().get(clientTable.getSelectedRow()).getId();
            try {
                userServiceRestClient.unbanClient(id);
                clientTableModel.setRowCount(0);
                userServiceRestClient.getClients().getContent().forEach(clientDto -> {
                    clientTableModel.addRow(new Object[]{clientDto.getEmail(), clientDto.isAccess(), clientDto.getId()});
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        banUnbanPanel.add(clientSide);

        JPanel managerSide = new JPanel();
        managerSide.setLayout(new BoxLayout(managerSide, BoxLayout.Y_AXIS));

        managerTableModel = new ManagerTableModel();
        managerTable = new JTable(managerTableModel);
        ManagerListDto managerListDto = userServiceRestClient.getManagers();
        managerListDto.getContent().forEach(managerDto -> {
            managerTableModel.addRow(new Object[]{managerDto.getEmail(), managerDto.isAccess(), managerDto.getId()});
        });
        JScrollPane managerTablePane = new JScrollPane();
        managerTablePane.setViewportView(managerTable);
        managerSide.add(managerTablePane);
        managerSide.add(Box.createRigidArea(new Dimension(0, 10)));
        managerSide.add(banManagerButton);
        banManagerButton.addActionListener(e -> {
            Long id = managerTableModel.getManagerListDto().getContent().get(managerTable.getSelectedRow()).getId();
            try {
                userServiceRestClient.banManager(id);
                managerTableModel.setRowCount(0);
                userServiceRestClient.getManagers().getContent().forEach(managerDto -> {
                    managerTableModel.addRow(new Object[]{managerDto.getEmail(), managerDto.isAccess(), managerDto.getId()});
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        managerSide.add(Box.createRigidArea(new Dimension(0, 10)));
        managerSide.add(unbanManagerButton);
        unbanManagerButton.addActionListener(e -> {
            Long id = managerTableModel.getManagerListDto().getContent().get(managerTable.getSelectedRow()).getId();
            try {
                userServiceRestClient.unbanManager(id);
                managerTableModel.setRowCount(0);
                userServiceRestClient.getManagers().getContent().forEach(managerDto -> {
                    managerTableModel.addRow(new Object[]{managerDto.getEmail(), managerDto.isAccess(), managerDto.getId()});
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        banUnbanPanel.add(Box.createRigidArea(new Dimension(30,0)));
        banUnbanPanel.add(managerSide);
//        banUnbanPanel.setLayout(new BoxLayout(banUnbanPanel, BoxLayout.Y_AXIS));
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        banUnbanPanel.add(banClientID);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        banUnbanPanel.add(banClientButton);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 30)));
//        banClientButton.addActionListener(e -> {
//            Long id = Long.parseLong(banClientID.getText());
//            try {
//                userServiceRestClient.banClient(id);
//                banClientID.setText("");
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//        banUnbanPanel.add(unbanClientID);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        banUnbanPanel.add(unbanClientButton);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 30)));
//        unbanClientButton.addActionListener(e -> {
//            Long id = Long.parseLong(unbanClientID.getText());
//            try {
//                userServiceRestClient.unbanClient(id);
//                unbanClientID.setText("");
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//        banUnbanPanel.add(banManagerID);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        banUnbanPanel.add(banManagerButton);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 30)));
//        banManagerButton.addActionListener(e -> {
//            Long id = Long.parseLong(banManagerID.getText());
//            try {
//                userServiceRestClient.banManager(id);
//                banManagerID.setText("");
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//        banUnbanPanel.add(unbanManagerID);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        banUnbanPanel.add(unbanManagerButton);
//        banUnbanPanel.add(Box.createRigidArea(new Dimension(0, 10)));
//        unbanManagerButton.addActionListener(e -> {
//            Long id = Long.parseLong(unbanManagerID.getText());
//            try {
//                userServiceRestClient.unbanManager(id);
//                unbanManagerID.setText("");
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });

        //TAB
        tabbedPane.addTab("Ban/Unban", banUnbanPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        //

        JPanel clientRankPanel = new JPanel();
        clientRankPanel.setLayout(new BoxLayout(clientRankPanel, BoxLayout.Y_AXIS));
        clientRankTableModel = new ClientRankTableModel();
        clientRankTable = new JTable(clientRankTableModel);

        ClientRankListDto clientRankListDto = userServiceRestClient.getClientRanks();
        clientRankListDto.getContent().forEach(clientRankDto -> {
            clientRankTableModel.addRow(new Object[]{clientRankDto.getName(), clientRankDto.getMinNumberOfReservations(), clientRankDto.getMaxNumberOfReservations(), clientRankDto.getDiscount()});
        });
        JScrollPane scrollPane = new JScrollPane(clientRankTable);
        clientRankPanel.add(scrollPane);
        clientRankPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        clientRankPanel.add(editCLientRank);
        clientRankPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        editCLientRank.addActionListener(e -> {
            String clientRankName = clientRankTableModel.getClientRankListDto().getContent().get(clientRankTable.getSelectedRow()).getName();
            new ClientRankUpdateView(clientRankName, this);

        });

        //TAB
        tabbedPane.addTab("Edit Rank", clientRankPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        //

        JPanel notificationTypePanel = new JPanel();
        notificationTypePanel.setLayout(new BoxLayout(notificationTypePanel, BoxLayout.Y_AXIS));
        notificationTypeTableModel = new NotificationTypeTableModel();
        notificationTypeTable = new JTable(notificationTypeTableModel);

        NotificationTypeListDto notificationTypeListDto = notificationServiceRestClient.getAllNotificationTypes();
        notificationTypeListDto.getContent().forEach(notificationTypeDto -> {
            notificationTypeTableModel.addRow(new Object[]{notificationTypeDto.getType(), notificationTypeDto.getMessage(), notificationTypeDto.getId()});
        });

        JScrollPane scrollPaneNotificationTypes = new JScrollPane(notificationTypeTable);
        notificationTypePanel.add(scrollPaneNotificationTypes);
        notificationTypePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JPanel splitNotificationType = new JPanel();
        splitNotificationType.setLayout(new BoxLayout(splitNotificationType, BoxLayout.LINE_AXIS));
        splitNotificationType.add(deleteNotificationTypeButton);
        splitNotificationType.add(Box.createRigidArea(new Dimension(60, 0)));



        deleteNotificationTypeButton.addActionListener(e -> {
            Long id = notificationTypeTableModel.getNotificationTypeListDto().getContent().get(notificationTypeTable.getSelectedRow()).getId();
            try {
                notificationServiceRestClient.deleteNotificationType(id);
                notificationTypeTableModel.setRowCount(0);
                notificationServiceRestClient.getAllNotificationTypes().getContent().forEach(notificationTypeDto -> {
                    notificationTypeTableModel.addRow(new Object[]{notificationTypeDto.getType(), notificationTypeDto.getMessage(), notificationTypeDto.getId()});
                });

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        splitNotificationType.add(updateNotificationTypeButton);
        notificationTypePanel.add(splitNotificationType);
        notificationTypePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        updateNotificationTypeButton.addActionListener( e -> {
            Long id = notificationTypeTableModel.getNotificationTypeListDto().getContent().get(notificationTypeTable.getSelectedRow()).getId();
            new NotificationTypeUpdateView(id, this);
        });

        //TAB
        tabbedPane.addTab("Notification Types", notificationTypePanel);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        //

        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        notificationTableModel = new NotificationTableModel();
        notificationTable = new JTable(notificationTableModel);

        NotificationListDto notificationListDto = notificationServiceRestClient.getAllNotifications();
        notificationListDto.getContent().forEach(notificationDto -> {
            notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
        });

        JScrollPane notificationsScrollPanel = new JScrollPane(notificationTable);
        notificationPanel.add(notificationsScrollPanel);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        notificationPanel.add(filterByEmailInput);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(filterByEmailButton);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        filterByEmailButton.addActionListener(e -> {
            String email = filterByEmailInput.getText();
            filterByEmailInput.setText("");
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

        notificationPanel.add(filterByTypeInput);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        notificationPanel.add(filterByTypeButton);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        filterByTypeButton.addActionListener(e -> {
            String type = filterByTypeInput.getText();
            filterByTypeInput.setText("");
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

        JPanel dates = new JPanel();
        dates.setLayout(new BoxLayout(dates, BoxLayout.LINE_AXIS));
        dates.add(Box.createRigidArea(new Dimension(5,0)));
        dates.add(new JLabel(" Between "));
        dates.add(filterByDate1);
        dates.add(new JLabel(" and "));
        dates.add(filterByDate2);
        dates.add(Box.createRigidArea(new Dimension(5,0)));
        notificationPanel.add(dates);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        notificationPanel.add(filterByDatesButton);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        filterByDatesButton.addActionListener(e -> {
            String date1 = filterByDate1.getText();
            String date2 = filterByDate2.getText();
            filterByDate1.setText("");
            filterByDate2.setText("");
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

        notificationPanel.add(resetFilterButton);
        notificationPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        resetFilterButton.addActionListener(e -> {
            filterByTypeInput.setText("");
            filterByDate1.setText("");
            filterByDate2.setText("");
            filterByEmailInput.setText("");
            notificationTableModel.setRowCount(0);
            notificationListDto.getContent().forEach(notificationDto -> {
                notificationTableModel.addRow(new Object[]{notificationDto.getEmail(), notificationDto.getType(), notificationDto.getDateCreated(), notificationDto.getMessage()});
            });
        });

        //TAB
        tabbedPane.addTab("Notifications", notificationPanel);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        //

        this.add(tabbedPane, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //TODO:
        //ban unban select from clients/managers list
        //get all users
    }

    public void updateRankTable() throws IOException {
        clientRankTableModel.setRowCount(0);
        ClientRankListDto clientRankListDto = userServiceRestClient.getClientRanks();
        clientRankListDto.getContent().forEach(clientRankDto -> {
            clientRankTableModel.addRow(new Object[]{clientRankDto.getName(), clientRankDto.getMinNumberOfReservations(), clientRankDto.getMaxNumberOfReservations(), clientRankDto.getDiscount()});
        });
    }

    public void updateNotificationTypeTable() throws IOException {
        notificationTypeTableModel.setRowCount(0);
        NotificationTypeListDto notificationTypeListDto = notificationServiceRestClient.getAllNotificationTypes();
        notificationTypeListDto.getContent().forEach(notificationTypeDto -> {
            notificationTypeTableModel.addRow(new Object[]{notificationTypeDto.getType(), notificationTypeDto.getMessage(), notificationTypeDto.getId()});
        });
    }

}
