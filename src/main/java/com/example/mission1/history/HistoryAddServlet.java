package com.example.mission1.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/History")
public class HistoryAddServlet extends HttpServlet {
    private final HistoryRepository historyRepository = new HistoryRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, NullPointerException {
        try {
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
            Timestamp inquiryDate = Timestamp.from(Instant.now());

            History history = new History(0, lat, lnt, inquiryDate); // id는 자동으로 증가하므로 0으로 설정
            historyRepository.createHistoryTable();
            historyRepository.insertHistoryTable(history);

            PrintWriter out = response.getWriter();
            out.println("데이터가 성공적으로 추가되었습니다. in Servlet");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
