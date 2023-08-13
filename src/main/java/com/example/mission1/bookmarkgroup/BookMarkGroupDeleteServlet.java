package com.example.mission1.bookmarkgroup;

import com.example.mission1.bookmark.BookMarkRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteBookmarkGroup")
public class BookMarkGroupDeleteServlet extends HttpServlet {
    private final BookMarkGroupRepository bookmarkgroupRepository = new BookMarkGroupRepository();
    private final BookMarkRepository bookmarkRepository = new BookMarkRepository();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String requestBody = sb.toString();

            // JSON 데이터 파싱
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(requestBody).getAsJsonObject();

            int id = jsonObject.get("id").getAsInt();
            String bookmarkGroupName = jsonObject.get("bookmarkGroupName").getAsString();
            boolean hasBookmarks = jsonObject.get("hasBookmarks").getAsBoolean();
            if (hasBookmarks) {
                bookmarkRepository.deleteBookmarksByGroupName(bookmarkGroupName);
                bookmarkgroupRepository.deleteBookmarkgroupById(id);
            } else {
                bookmarkgroupRepository.deleteBookmarkgroupById(id);
            }
         // 응답 데이터 전송
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            e.printStackTrace();
        }
    }
}
