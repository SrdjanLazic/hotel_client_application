package raf.hotelclientapplication;

import raf.hotelclientapplication.model.User;
import raf.hotelclientapplication.view.HomeView;

import javax.swing.*;
import java.awt.*;

public class HotelClientApplication extends JFrame {

    // Ovde token da bi bio svuda dostupan nakon logina
    private String token;
    private HomeView homeView;
    private User user;

    private HotelClientApplication() throws IllegalAccessException, NoSuchMethodException {
        this.setTitle("Client Application");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        homeView = new HomeView();
        this.add(homeView, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static class InstanceHolder {

        private static HotelClientApplication instance;

        static {
            try {
                instance = new HotelClientApplication();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    public static HotelClientApplication getInstance() {
        return InstanceHolder.instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HomeView getHomeView() {
        return homeView;
    }

    public void setHomeView(HomeView homeView) {
        this.homeView = homeView;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
