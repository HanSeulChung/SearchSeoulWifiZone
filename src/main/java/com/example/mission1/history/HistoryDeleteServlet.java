package com.example.mission1.history;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/history-delete")
public class HistoryDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // history-delete.jsp에서 넘어온 ID 파라미터를 받음
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                // ID를 사용하여 데이터베이스에서 해당 히스토리 삭제하는 로직을 추가
                HistoryRepository historyRepository = new HistoryRepository();
                historyRepository.deleteHistoryById(id);
                System.out.printf("ID=%d 가 삭제되었습니다", id);
            } catch (NumberFormatException e) {
                // ID 파라미터를 정수로 변환하는데 실패한 경우 예외 처리
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 ID 값입니다.");
            }
        } else {
            // ID 파라미터가 없는 경우 예외 처리
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID 값이 필요합니다.");
        }
    }
}
