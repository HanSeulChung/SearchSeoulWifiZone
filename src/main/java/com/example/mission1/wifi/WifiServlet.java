package com.example.mission1.wifi;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/Wifi")
public class WifiServlet extends HttpServlet {
    private final WifiRepository wifiRepository = new WifiRepository();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = request.getReader();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }

            // JSON 문자열을 JSON 객체로 파싱
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(stringBuilder.toString()).getAsJsonObject();

            // JSON 객체에서 원하는 데이터를 가져옴
            double lat = jsonObject.get("lat").getAsDouble();
            double lnt = jsonObject.get("lnt").getAsDouble();

            wifiRepository.setNullDistance();
            wifiRepository.setNearlyDistance(lat, lnt);
            List<Wifi> wifiData = wifiRepository.getNearlyWifi();
            System.out.println(wifiData);
            String jsonData = new Gson().toJson(wifiData);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonData);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
