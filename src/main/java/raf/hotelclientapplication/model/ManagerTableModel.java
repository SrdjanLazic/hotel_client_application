package raf.hotelclientapplication.model;

import raf.hotelclientapplication.restclient.dto.ClientDto;
import raf.hotelclientapplication.restclient.dto.ClientListDto;
import raf.hotelclientapplication.restclient.dto.ManagerDto;
import raf.hotelclientapplication.restclient.dto.ManagerListDto;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ManagerTableModel extends DefaultTableModel {

    private ManagerListDto managerListDto = new ManagerListDto();

    public ManagerTableModel() {
        super(new String[]{"Email", "Has access?"}, 0);
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
        ManagerDto managerDto = new ManagerDto();
        managerDto.setEmail(String.valueOf(rowData[0]));
        managerDto.setAccess(Boolean.parseBoolean(String.valueOf(rowData[1])));
        managerDto.setId(Long.parseLong(String.valueOf(rowData[2])));
        managerListDto.getContent().add(managerDto);
    }

    public ManagerListDto getManagerListDto() {
        return managerListDto;
    }

    public void setManagerListDto(ManagerListDto managerListDto) {
        this.managerListDto = managerListDto;
    }
}
