package com.example.mission1.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet("/history-delete")
public class HistoryDeleteServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // history.jsp에서 넘어온 ID 파라미터를 받음
        String bodyJson = "";

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = null;
        //한줄씩 담을 변수
        String line = "";

        try {
            //body내용 inputstream에 담는다.
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                br = new BufferedReader(new InputStreamReader(inputStream));
                //더 읽을 라인이 없을때까지 계속
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }else {
                System.out.println("Data 없음");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID 값이 필요합니다.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            HistoryRepository historyRepository = new HistoryRepository();
            historyRepository.deleteHistoryById(id);
            System.out.printf("ID=%d 가 삭제되었습니다", id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 ID 값입니다.");
        }
    }
}
