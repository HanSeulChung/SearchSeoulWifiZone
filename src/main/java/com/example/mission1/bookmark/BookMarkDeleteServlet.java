package com.example.mission1.bookmark;

import com.example.mission1.bookmarkgroup.BookMarkGroupRepository;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteBookmark")
public class BookMarkDeleteServlet extends HttpServlet {
    private final BookMarkRepository bookmarkRepository = new BookMarkRepository();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        bookmarkRepository.deleteBookmarkById(id);

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
