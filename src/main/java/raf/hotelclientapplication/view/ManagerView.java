package raf.hotelclientapplication.view;

import javax.swing.*;

public class ManagerView extends JFrame{

    ManagerView(){
        JFrame frame = new AdminView();
        frame.setTitle("MANAGER");
        frame.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button1 = new JButton("Press");
        frame.getContentPane().add(button1);
        frame.setVisible(true);
    }



    //update profile - bespotrebno za admina mozda?
    //update client password
    //get discount
    //change password - bespotrebno za admina mozda?
    //list all notifications
    //list clients
    //list managers
}
