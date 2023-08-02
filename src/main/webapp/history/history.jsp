<%--
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
        #wifiInfos {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #wifiInfos td, #wifiInfos th {
            border: 1px solid #ddd;
            text-align: left;
            padding: 8px;
        }

        #wifiInfos tr:nth-child(even){background-color: #f2f2f2;}

        #wifiInfos tr:hover {background-color: #ddd;}

        #wifiInfos th {
            /*padding-top: 12px;*/
            /*padding-bottom: 12px;*/
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<h1><%= "위치 히스토리 목록" %></h1>
<br/>
<a href="http://localhost:8080">홈 </a>
<a> | </a>
<a href="history.jsp">위치 히스토리 목록 </a>
<a> | </a>
<a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<a> | </a>
<a href="hello-servlet">Hello Servlet</a>
<p></p>
<table id="wifiInfos">
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    <tr>
        <td colspan="5">조회된 위치 정보가 존재하지 않습니다.</td>
    </tr>
</table>
</body>
</html>