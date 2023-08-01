<%--
  Created by IntelliJ IDEA.
  User: w0w12
  Date: 2023-07-26
  Time: 오후 9:45
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        #bookmarkGroup {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
            padding-top: 12px;
        }

        #bookmarkGroup td, #bookmarkGroup th {
            border: 1px solid #ddd;
            text-align: left;
            padding: 8px;
        }

        #bookmarkGroup tr:nth-child(even){background-color: #f2f2f2;}

        #bookmarkGroup tr:hover {background-color: #ddd;}

        #bookmarkGroup th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
        #bookmarkGroup td {
            text-align: center;
            padding-top: 20px;
            padding-bottom: 20px;
        }
    </style>
</head>
<body>
<h1><%= "북마크 그룹" %></h1>
<a href=" ">홈 </a>
<a> | </a>
<a href="history.jsp">위치 히스토리 목록 </a>
<a> | </a>
<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<a> | </a>
<a href="bookmark-list-view.jsp">북마크 보기</a>
<a> | </a>
<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<p></p>


</body>
</html>
