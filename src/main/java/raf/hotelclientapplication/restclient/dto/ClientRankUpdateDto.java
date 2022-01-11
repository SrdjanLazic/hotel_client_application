package raf.hotelclientapplication.restclient.dto;


public class ClientRankUpdateDto {

    private Integer minNumberOfReservations;
    private Integer maxNumberOfReservations;
    private Double discount;

    public ClientRankUpdateDto() {
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
}
