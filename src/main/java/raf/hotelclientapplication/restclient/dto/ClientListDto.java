package raf.hotelclientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientListDto {

    private List<ClientDto> content = new ArrayList<>();

    public ClientListDto(){

    }

    public List<ClientDto> getContent() {
        return content;
    }

    public void setContent(List<ClientDto> content) {
        this.content = content;
    }
}
