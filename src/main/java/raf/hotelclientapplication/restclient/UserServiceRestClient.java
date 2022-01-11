package raf.hotelclientapplication.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import raf.hotelclientapplication.restclient.dto.*;

import java.io.IOException;

public class UserServiceRestClient {

    public static final String URL = "http://localhost:8080/api";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    OkHttpClient manager = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();


    public String login(String email, String password, String role) throws IOException {

        // posto ruta login u user servisu prima kao body tokenrequestdto, moramo da ga kreiramo ovde da bismo poslali na tu rutu kao JSON
        TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);

        // zapisujemo TokenRequestDto kao JSON
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

        Request request = new Request.Builder()
                .url(URL + String.format("/%s/login", role)) // URL koji gadjamo
                .post(body) // saljemo JSON kao body (username, password)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        // ako je login vratio 200, deserijalizujemo i dobijamo TokenResponseDto koji sadrzi token, koji vracamo u View da ga setuje globalno
        if (response.code() == 200) {
            String json = response.body().string();
            TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

            return dto.getToken();
        }

        throw new RuntimeException("Invalid username or password");
    }

    public void registerClient(ClientCreateDto clientCreateDto) throws IOException {
        System.out.println(clientCreateDto.getUsername());
        //.create je depracated
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientCreateDto));

        Request request = new Request.Builder()
                .url(URL + "/client") // URL koji gadjamo
                .post(body) // saljemo JSON kao body (username, password)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        // ako je login vratio 200, deserijalizujemo i dobijamo TokenResponseDto koji sadrzi token, koji vracamo u View da ga setuje globalno
        if (response.code() == 201) {
            String json = response.body().string();
            ClientDto dto = objectMapper.readValue(json, ClientDto.class);
        }


    }

    public void registerManager(ManagerCreateDto managerCreateDto) throws IOException{
        System.out.println(managerCreateDto.getUsername());
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerCreateDto));

        Request request = new Request.Builder()
                .url(URL + "/manager") // URL koji gadjamo
                .post(body) // saljemo JSON kao body (username, password)
                .build();

        Call call = manager.newCall(request);

        Response response = call.execute();

        // ako je login vratio 200, deserijalizujemo i dobijamo TokenResponseDto koji sadrzi token, koji vracamo u View da ga setuje globalno
        if (response.code() == 201) {
            String json = response.body().string();
            ManagerDto dto = objectMapper.readValue(json, ManagerDto.class);
        }
    }
}
