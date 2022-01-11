package raf.hotelclientapplication.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.restclient.dto.ClientRankListDto;
import raf.hotelclientapplication.restclient.dto.NotificationTypeListDto;

import java.io.IOException;

public class NotificationServiceRestClient {

    public static final String URL = "http://localhost:8081/api";

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    ObjectMapper objectMapper = new ObjectMapper();
    OkHttpClient client = new OkHttpClient();

    public NotificationTypeListDto getAllNotificationTypes() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/notification-type")
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();

            return objectMapper.readValue(json, NotificationTypeListDto.class);
        }
        throw new RuntimeException();
    }


    public void deleteNotificationType(Long id) throws IOException {
        Request request = new Request.Builder()
                .url(URL + "/notification-type/" + id)
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .delete()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
        } else {
            throw new RuntimeException();
        }
    }
}
