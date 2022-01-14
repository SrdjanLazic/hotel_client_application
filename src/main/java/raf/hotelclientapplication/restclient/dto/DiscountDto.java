package raf.hotelclientapplication.restclient.dto;

public class DiscountDto {

    private Double discount;

    public DiscountDto() {
    }

    public DiscountDto(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
