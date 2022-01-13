package raf.hotelclientapplication.model;

import raf.hotelclientapplication.restclient.dto.NotificationDto;
import raf.hotelclientapplication.restclient.dto.NotificationListDto;

import javax.swing.table.DefaultTableModel;
import java.time.Instant;
import java.time.LocalDate;

public class NotificationTableModel extends DefaultTableModel {

    private NotificationListDto notificationListDto = new NotificationListDto();

    public NotificationTableModel(){
        super(new String[]{"Sent to", "Type", "Date sent", "Message"}, 0);
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEmail(String.valueOf(rowData[0]));
        notificationDto.setType((NotificationType) rowData[1]);
        notificationDto.setDateCreated((LocalDate) rowData[2]);
        notificationDto.setMessage(String.valueOf(rowData[3]));
        notificationListDto.getContent().add(notificationDto);

    }

    public NotificationListDto getNotificationListDto() {
        return notificationListDto;
    }

    public void setNotificationListDto(NotificationListDto notificationListDto) {
        this.notificationListDto = notificationListDto;
    }
}
