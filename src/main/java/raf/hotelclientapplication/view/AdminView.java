package raf.hotelclientapplication.view;

import javax.swing.*;

public class AdminView extends JFrame{

    AdminView() {
        JFrame frame = new AdminView();
        frame.setTitle("ADMIN");
        frame.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button1 = new JButton("Press");
        frame.getContentPane().add(button1);
        frame.setVisible(true);

        //ban
        //unban
        //update profile - bespotrebno za admina mozda?
        //update client password
        //change password - bespotrebno za admina mozda?
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
