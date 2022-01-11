package raf.hotelclientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class NotificationTypeListDto {

    private List<NotificationTypeDto> content = new ArrayList<>();

    public NotificationTypeListDto(){

    }

    public NotificationTypeListDto(List<NotificationTypeDto> content) {
        this.content = content;
    }

    public List<NotificationTypeDto> getContent() {
        return content;
    }

    public void setContent(List<NotificationTypeDto> content) {
        this.content = content;
    }
}
