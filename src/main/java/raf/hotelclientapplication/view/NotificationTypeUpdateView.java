package raf.hotelclientapplication.view;

import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NotificationTypeUpdateView extends JDialog{

    private JLabel notificationTypeName;
    private JLabel messageLabel = new JLabel("Message:");
    private JTextArea messageArea = new JTextArea(5,10);
    private JButton messageButton = new JButton("Update");
    private NotificationServiceRestClient notificationServiceRestClient = new NotificationServiceRestClient();
    private JDialog parentView;


    public NotificationTypeUpdateView(Long id, JDialog adminView) {
        parentView = adminView;
        this.setTitle("Client rank update");
        this.setSize(400,200);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
//        notificationTypeName = new JLabel("Currently editing: " + id);
//        add(notificationTypeName);
        add(messageLabel);
        add(messageArea);
        add(messageButton);
        messageButton.addActionListener(e -> {
            String message = messageArea.getText();
            try {
                notificationServiceRestClient.updateNotificationType(message, id);
                parentView.setVisible(false);
                new AdminView();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            this.setVisible(false);
        });

        this.pack();

    }
}
