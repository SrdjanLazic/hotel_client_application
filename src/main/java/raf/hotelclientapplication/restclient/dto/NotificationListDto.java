package raf.hotelclientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class NotificationListDto {

    List<NotificationDto> content = new ArrayList<>();

    public NotificationListDto() {
    }

    public NotificationListDto(List<NotificationDto> content) {
        this.content = content;
    }

    public List<NotificationDto> getContent() {
        return content;
    }

    public void setContent(List<NotificationDto> content) {
        this.content = content;
    }
}
