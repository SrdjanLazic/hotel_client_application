package raf.hotelclientapplication.view;

import raf.hotelclientapplication.restclient.NotificationServiceRestClient;
import raf.hotelclientapplication.restclient.UserServiceRestClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NotificationTypeUpdateView extends JDialog{

    private JLabel notificationTypeName;
    private JLabel messageLabel = new JLabel("Message:");
    private JTextField messageArea = new JTextField(10);
    private JButton messageButton = new JButton("Update");
    private NotificationServiceRestClient notificationServiceRestClient = new NotificationServiceRestClient();
    private JDialog parentView;


    public NotificationTypeUpdateView(Long id, JDialog adminView) {
        parentView = adminView;
        this.setTitle("Client rank update");
        this.setSize(400,200);
        JPanel messageSpace = new JPanel();
        messageSpace.setLayout(new BoxLayout(messageSpace, BoxLayout.Y_AXIS));
//        notificationTypeName = new JLabel("Currently editing: " + id);
//        add(notificationTypeName);
        messageSpace.add(Box.createRigidArea(new Dimension(20,20)));
        messageSpace.add(messageLabel);
        messageSpace.add(Box.createRigidArea(new Dimension(0,10)));
        messageSpace.add(messageArea);
        messageSpace.add(Box.createRigidArea(new Dimension(0,30)));
        messageSpace.add(messageButton);
        messageSpace.add(Box.createRigidArea(new Dimension(20,20)));
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

        this.add(messageSpace);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);

    }
}
