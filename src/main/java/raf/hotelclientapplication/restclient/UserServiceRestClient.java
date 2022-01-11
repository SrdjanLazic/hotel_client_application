package raf.hotelclientapplication.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import raf.hotelclientapplication.HotelClientApplication;
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
            // TODO:
            //ManagerDto dto = objectMapper.readValue(json, ManagerDto.class);
        }
    }

    public void banClient(Long id) throws IOException {
        String token = HotelClientApplication.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(id));

        //@PutMapping("/{id}/ban")
        Request request = new Request.Builder()
                .url(URL + String.format("/client/%d/ban", id)) // URL koji gadjamo
                .put(body)
                .header("Authorization", "Bearer " + token)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
        } else {
            throw new RuntimeException();
        }
    }

    public void unbanClient(Long id) throws IOException {
        String token = HotelClientApplication.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(id));

        //@PutMapping("/{id}/ban")
        Request request = new Request.Builder()
                .url(URL + String.format("/client/%d/unban", id)) // URL koji gadjamo
                .put(body)
                .header("Authorization", "Bearer " + token)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
        } else {
            throw new RuntimeException();
        }
    }

    public void banManager(Long id) throws IOException {
        String token = HotelClientApplication.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(id));


        Request request = new Request.Builder()
                .url(URL + String.format("/manager/%d/ban", id)) // URL koji gadjamo
                .put(body)
                .header("Authorization", "Bearer " + token)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
        } else {
            throw new RuntimeException();
        }
    }

    public void unbanManager(Long id) throws IOException {
        String token = HotelClientApplication.getInstance().getToken();
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(id));

        Request request = new Request.Builder()
                .url(URL + String.format("/manager/%d/unban", id)) // URL koji gadjamo
                .put(body)
                .header("Authorization", "Bearer " + token)
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
        } else {
            throw new RuntimeException();
        }
    }

    public ClientRankListDto getClientRanks() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/clientrank")
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();

            return objectMapper.readValue(json, ClientRankListDto.class);
        }

        throw new RuntimeException();
    }

    public ClientRankDto updateClientRank(Integer min, Integer max, Double discount, String name) throws IOException {

        ClientRankUpdateDto clientRankUpdateDto = new ClientRankUpdateDto();
        clientRankUpdateDto.setDiscount(discount);
        clientRankUpdateDto.setMaxNumberOfReservations(max);
        clientRankUpdateDto.setMinNumberOfReservations(min);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientRankUpdateDto));

        Request request = new Request.Builder()
                .url(URL + "/clientrank/" + name + "/update")
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .put(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();

            return objectMapper.readValue(json, ClientRankDto.class);
        }

        throw new RuntimeException();

    }
}
