package com.example.mission1.api;
//http://openapi.seoul.go.kr:8088/7648616b6877307736356369546258/json/TbPublicWifiInfo

import com.example.mission1.wifi.Wifi;
import com.example.mission1.wifi.WifiRepository;
import com.google.gson.*;
import lombok.Getter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
public class OpenAPI {
    private String baseUrl = "http://openapi.seoul.go.kr:8088/";
    private String apiKey = "7648616b6877307736356369546258";
    private int totalCnt;

    public void fetchAndStoreData() throws IOException {
        int page = 1;
        int pageSize = 1000; // 한 번에 가져올 데이터 개수

        WifiRepository wifidb = new WifiRepository();
        wifidb.createWifiTable();
        wifidb.deleteAlldata();
        while (true) {
            String apiUrl = baseUrl + apiKey + "/json/TbPublicWifiInfo/" + page + "/" + (page + pageSize - 1) + "/";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                br.close();
                conn.disconnect();

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

                JsonArray rows = jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");

                // JSON 데이터를 배열로 변환
                Wifi[] wifiInfoArray = gson.fromJson(rows, Wifi[].class);
                totalCnt += wifiInfoArray.length;
                // DB에 저장
                wifidb.insertWifiTable(wifiInfoArray);

                // 다음 페이지로 이동
                page += pageSize;

                // 더 이상 데이터가 없는 경우 종료
                if (wifiInfoArray.length < pageSize) {
                    break;
                }
            } else {
                conn.disconnect();
                throw new IOException("API 요청에 실패했습니다. HTTP 에러 코드: " + conn.getResponseCode());
            }
        }
    }
}
