<%@ page import="com.example.mission1.history.HistoryRepository" %>
<%@ page import="com.example.mission1.history.History" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: w0w12
  Date: 2023-07-26
  Time: 오후 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        #historyInfos {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
            padding-top: 12px;
        }

        #historyInfos td, #historyInfos th {
            border: 1px solid #ddd;
            text-align: left;
            padding: 8px;
        }

        #historyInfos tr:nth-child(even){background-color: #f2f2f2;}

        #historyInfos tr:hover {background-color: #ddd;}

        #historyInfos th {
            /*padding-top: 12px;*/
            /*padding-bottom: 12px;*/
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>

    <script>
        function confirmDelete(id) {
            if (confirm("삭제하시겠습니까?")) {
                // 확인 버튼을 누른 경우
                // 서블릿으로 삭제 요청을 보냄
                const xhr = new XMLHttpRequest();
                const url = "history-delete?id=" + id;
                xhr.open("DELETE", url, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            // 성공적으로 삭제되면 history.jsp로 리다이렉트
                            window.location.href = "history.jsp";
                        } else {
                            // 삭제에 실패한 경우 예외 처리
                            alert("삭제에 실패하였습니다.");
                        }
                    }
                };
                xhr.send();
            } else {
                // 취소 버튼을 누른 경우 아무 동작 안 함
            }
        }
    </script>
</head>
<body>
<h1><%= "위치 히스토리 목록" %></h1>
<a href="/">홈 </a>
<a> | </a>
<a href="history.jsp">위치 히스토리 목록 </a>
<a> | </a>
<a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<a> | </a>
<a href="../bookmark-list-view.jsp">북마크 보기</a>
<a> | </a>
<a href="../bookmarkgroup/bookmark-group.jsp">북마크 그룹 관리</a>
<p></p>
<table id="historyInfos">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <%
        HistoryRepository historyRepository = new HistoryRepository();
        List<History> historyList = historyRepository.getAllHistory(); // 예시 메서드 이름

        if (historyList.isEmpty()) {
    %>
    <tr>
        <td colspan="5">조회된 위치 정보가 존재하지 않습니다.</td>
    </tr>
    <% } else {
        for (History history : historyList) { %>
    <tr>
        <td><%= history.getId() %></td>
        <td><%= history.getLat() %></td>
        <td><%= history.getLnt() %></td>
        <td><%= history.getInquiryDate() %></td>
        <td><button onclick="confirmDelete(<%= history.getId() %>)">삭제</button></td>
    </tr>
    <% } } %>
</table>
</body>
</html>