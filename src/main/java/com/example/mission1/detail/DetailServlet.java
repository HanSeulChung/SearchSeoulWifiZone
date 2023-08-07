package com.example.mission1.detail;

import com.example.mission1.wifi.Wifi;
import com.example.mission1.wifi.WifiRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {

    private final WifiRepository wifiRepository = new WifiRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청으로부터 X_SWIFI_MGR_NO 파라미터 받기
        String manageNo = request.getParameter("manageNo");

        // 데이터베이스에서 X_SWIFI_MGR_NO를 기반으로 와이파이 정보 조회하기
        Wifi wifiInfo = wifiRepository.getWifiInfoByManageNo(manageNo); // 이 메소드는 데이터베이스에서 정보를 가져오도록 구현되어야 합니다.

        // 와이파이 정보를 request의 attribute로 설정
        request.setAttribute("wifiInfo", wifiInfo);

        // detail.jsp로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("detail.jsp");
        dispatcher.forward(request, response);
    }
}





