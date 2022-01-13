package raf.hotelclientapplication.model;

import raf.hotelclientapplication.restclient.dto.ClientDto;
import raf.hotelclientapplication.restclient.dto.ClientListDto;

import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class ClientTableModel extends DefaultTableModel {

    private ClientListDto clientListDto = new ClientListDto();

    public ClientTableModel(){
        super(new String[]{"Email", "Has access?"}, 0);
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
        ClientDto clientDto = new ClientDto();
        clientDto.setEmail(String.valueOf(rowData[0]));
        clientDto.setAccess(Boolean.parseBoolean(String.valueOf(rowData[1])));
        clientDto.setId(Long.parseLong(String.valueOf(rowData[2])));
        clientListDto.getContent().add(clientDto);
    }

    public ClientListDto getClientListDto() {
        return clientListDto;
    }

    public void setClientListDto(ClientListDto clientListDto) {
        this.clientListDto = clientListDto;
    }
}
