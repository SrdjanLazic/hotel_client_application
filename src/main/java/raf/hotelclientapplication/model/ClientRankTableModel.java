package raf.hotelclientapplication.model;

import raf.hotelclientapplication.restclient.dto.ClientRankDto;
import raf.hotelclientapplication.restclient.dto.ClientRankListDto;

import javax.swing.table.DefaultTableModel;

public class ClientRankTableModel extends DefaultTableModel {

    private ClientRankListDto clientRankListDto = new ClientRankListDto();

    public ClientRankTableModel() throws IllegalAccessException, NoSuchMethodException {
        super(new String[]{"Name", "Minimum number of reservations", "Maximum number of reservations", "Discount"}, 0);
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
        ClientRankDto clientRankDto = new ClientRankDto();
        clientRankDto.setName(String.valueOf(rowData[0]));
        clientRankDto.setMinNumberOfReservations(Integer.parseInt(String.valueOf(rowData[1])));
        clientRankDto.setMaxNumberOfReservations(Integer.parseInt(String.valueOf(rowData[2])));
        clientRankDto.setDiscount(Double.parseDouble(String.valueOf(rowData[3])));
        clientRankListDto.getContent().add(clientRankDto);


    }

    public ClientRankListDto getClientRankListDto() {
        return clientRankListDto;
    }

    public void setClientRankListDto(ClientRankListDto clientRankListDto) {
        this.clientRankListDto = clientRankListDto;
    }
}
