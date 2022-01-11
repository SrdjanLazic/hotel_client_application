package raf.hotelclientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientRankListDto {

    private List<ClientRankDto> content = new ArrayList<>();

    public ClientRankListDto(){

    }

    public ClientRankListDto(List<ClientRankDto> content) {
        this.content = content;
    }

    public List<ClientRankDto> getContent() {
        return content;
    }

    public void setContent(List<ClientRankDto> content) {
        this.content = content;
    }
}
