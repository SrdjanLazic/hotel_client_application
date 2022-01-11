package raf.hotelclientapplication.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.*;
import raf.hotelclientapplication.HotelClientApplication;
import raf.hotelclientapplication.restclient.dto.ClientRankListDto;
import raf.hotelclientapplication.restclient.dto.NotificationListDto;
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

    public NotificationListDto getAllNotifications() throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        Request request = new Request.Builder()
                .url(URL + "/notification")
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();


            return objectMapper.readValue(json, NotificationListDto.class);
        }
        throw new RuntimeException();

    }


    public NotificationListDto getNotificationsByEmail(String email) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        Request request = new Request.Builder()
                .url(URL + String.format("/notification/filter/email=%s", email))
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();


            return objectMapper.readValue(json, NotificationListDto.class);
        }
        throw new RuntimeException();
    }

    public NotificationListDto getNotificationsByType(String type) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        Request request = new Request.Builder()
                .url(URL + String.format("/notification/filter/type=%s", type))
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();


            return objectMapper.readValue(json, NotificationListDto.class);
        }
        throw new RuntimeException();
    }

    public NotificationListDto getNotificationsByDate(String date1, String date2) throws IOException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);


        System.out.println(URL + String.format("/notification/filter/between=%s,%s", date1, date2));

        Request request = new Request.Builder()
                .url(URL + String.format("/notification/filter/between=%s,%s", date1, date2))
                .header("Authorization", "Bearer " + HotelClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();


            return objectMapper.readValue(json, NotificationListDto.class);
        }
        throw new RuntimeException();
    }
}
