package raf.hotelclientapplication.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.restclient.UserServiceRestClient;
import raf.hotelclientapplication.restclient.dto.ClientDto;
import raf.hotelclientapplication.restclient.dto.ClientUpdateDto;
import raf.hotelclientapplication.restclient.dto.DiscountDto;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DiscountWindow extends JDialog {

    private UserServiceRestClient userServiceRestClient = new UserServiceRestClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    DiscountWindow(Long id) throws IOException {
        this.setTitle("Your Discount IS:");

        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        DiscountDto discountDto = userServiceRestClient.getDiscount(id);
        Double result = 100 - discountDto.getDiscount()*100;

        updatePanel.add(new JLabel("Your current discount is:"));
        updatePanel.add(new JLabel(result.intValue() + "%"));

        updatePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        JButton ok =  new JButton("OK");
        ok.addActionListener(e->{
            this.dispose();
        });
        updatePanel.add(ok);



        add(updatePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
