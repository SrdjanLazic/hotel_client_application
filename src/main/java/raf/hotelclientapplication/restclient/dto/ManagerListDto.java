package raf.hotelclientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class ManagerListDto {

    private List<ManagerDto> content = new ArrayList<>();

    public ManagerListDto() {
    }

    public List<ManagerDto> getContent() {
        return content;
    }

    public void setContent(List<ManagerDto> content) {
        this.content = content;
    }
}
