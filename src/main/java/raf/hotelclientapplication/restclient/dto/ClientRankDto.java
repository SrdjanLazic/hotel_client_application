package raf.hotelclientapplication.restclient.dto;

public class ClientRankDto {

    private String name;
    private Integer minNumberOfReservations;
    private Integer maxNumberOfReservations;
    private Double discount;

    public ClientRankDto() {
    }

    public Integer getMinNumberOfReservations() {
        return minNumberOfReservations;
    }

    public void setMinNumberOfReservations(Integer minNumberOfReservations) {
        this.minNumberOfReservations = minNumberOfReservations;
    }

    public Integer getMaxNumberOfReservations() {
        return maxNumberOfReservations;
    }

    public void setMaxNumberOfReservations(Integer maxNumberOfReservations) {
        this.maxNumberOfReservations = maxNumberOfReservations;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClientRankDto{" +
                "name='" + name + '\'' +
                ", minNumberOfReservations=" + minNumberOfReservations +
                ", maxNumberOfReservations=" + maxNumberOfReservations +
                ", discount=" + discount +
                '}';
    }
}
