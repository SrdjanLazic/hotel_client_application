package raf.hotelclientapplication.model;

import raf.hotelclientapplication.restclient.dto.NotificationTypeDto;
import raf.hotelclientapplication.restclient.dto.NotificationTypeListDto;

import javax.swing.table.DefaultTableModel;

public class NotificationTypeTableModel extends DefaultTableModel {

    private NotificationTypeListDto notificationTypeListDto = new NotificationTypeListDto();

    public NotificationTypeTableModel() {
        super(new String[]{"Type", "Message"}, 0);
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();
        notificationTypeDto.setType(Type.valueOf(String.valueOf(rowData[0])));
        notificationTypeDto.setMessage(String.valueOf(rowData[1]));
        notificationTypeDto.setId(Long.valueOf(String.valueOf(rowData[2])));
        notificationTypeListDto.getContent().add(notificationTypeDto);


    }

    public NotificationTypeListDto getNotificationTypeListDto() {
        return notificationTypeListDto;
    }

    public void setNotificationTypeListDto(NotificationTypeListDto notificationTypeListDto) {
        this.notificationTypeListDto = notificationTypeListDto;
    }
}
