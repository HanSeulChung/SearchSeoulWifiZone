package com.example.mission1.history;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/history-delete")
public class HistoryDeleteServlet extends HttpServlet {
    private final HistoryRepository historyRepository = new HistoryRepository();
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // history.jsp에서 넘어온 ID 파라미터를 받음
//        System.out.println(request);
//        String idStr = request.getParameter("id");
//        if (idStr == null || idStr.isEmpty()) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID 값이 필요합니다.");
//            return;
//        }
//
//        try {
//            int id = Integer.parseInt(idStr);
//            historyRepository.deleteHistoryById(id);
//            System.out.printf("ID=%d 가 삭제되었습니다", id);
//            response.setStatus(HttpServletResponse.SC_OK);
//        } catch (NumberFormatException e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 ID 값입니다.");
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // history.jsp에서 넘어온 ID 파라미터를 받음
        System.out.println(request);
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID 값이 필요합니다.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            HistoryRepository historyRepository = new HistoryRepository();
            historyRepository.deleteHistoryById(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 ID 값입니다.");
        }
    }
}
