package com.example.mission1;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "getOpenAPI", value = "/load-wifi")
public class GetOpenAPI extends HttpServlet {
    private String message;
    private String url = "http://openapi.seoul.go.kr:8088/7648616b6877307736356369546258/json/TbPublicWifiInfo";


    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        String getTotalCount = getWifiTotalCount(url);
        JsonObject json_totalCount = new Gson().fromJson(getTotalCount, JsonObject.class);
        int totalCount = Integer.parseInt(json_totalCount.getAsJsonObject("TbPublicWifiInfo").get("list_total_count").toString());

        PrintWriter out = response.getWriter();
        out.println("<html>\n" +
                "<head>\n" +
                "    <title>와이파이 정보 구하기</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"text-align: center\">");
        out.println("<h1>"+totalCount+"개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>");
        out.println("<a href=\"index.jsp\">홈 으로 가기 </a>");
        out.println("</div>\n" +
                "</body>\n" +
                "</html>\n");
        JsonArray jsonArray = json_totalCount.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
    }

    String getWifiTotalCount(String url) {
        String result = null;
        try {
            OkHttpClient client = new OkHttpClient();
            url = url + "/1/1/";
            Request rq = new Request.Builder()
                    .url(url)
                    .build();
            Response rs = client.newCall(rq).execute();
            result = rs.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void destroy() {
    }
}