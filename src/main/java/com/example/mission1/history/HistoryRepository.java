package com.example.mission1.history;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    private String dbUrl = "jdbc:sqlite:C:/Users/w0w12/Java/mission/mission1/src/main/java/db/seoulWifi.db";
    public void createHistoryTable() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(dbUrl);
                 Statement statement = connection.createStatement()) {
                String createTableSql = "CREATE TABLE IF NOT EXISTS LOCATION_HISTORY (" +
                        "ID  INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "LAT DOUBLE," +
                        "LNT DOUBLE," +
                        "INQUIRY_DATE TEXT" +
                        ");";
                // 테이블 생성 쿼리 실행
                statement.execute(createTableSql);

                System.out.println("LOCATION_HISTORY 테이블이 성공적으로 생성되었습니다.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver 찾지 못하였습니다.");
            e.printStackTrace();
        }
    }
    public void insertHistoryTable(History history) {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            String insertSql = "INSERT INTO LOCATION_HISTORY (LAT, LNT, INQUIRY_DATE) VALUES (?, ?, ?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                preparedStatement.setDouble(1, history.getLat());
                preparedStatement.setDouble(2, history.getLnt());
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                String inquiryDateStr = history.getInquiryDate().format(formatter);
                preparedStatement.setString(3, inquiryDateStr);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteAlldata() {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            String deleteAllSql = "DELETE FROM LOCATION_HISTORY";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAllSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteHistoryById(int id) {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(dbUrl)) {
                String deleteSql = "DELETE FROM LOCATION_HISTORY WHERE ID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
                    preparedStatement.setInt(1, id);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch(ClassNotFoundException e) {
        e.printStackTrace();
        }
    }

    // 모든 히스토리 정보를 조회하는 메서드
    public List<History> getAllHistory() {
        List<History> historyList = new ArrayList<>();
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(dbUrl);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM LOCATION_HISTORY ORDER BY ID DESC")) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    double lat = rs.getDouble("lat");
                    double lnt = rs.getDouble("lnt");
                    String inquiryDateStr = rs.getString("inquiry_Date");
                    LocalDateTime inquiryDate = LocalDateTime.parse(inquiryDateStr);
                    History history = new History(id, lat, lnt, inquiryDate);
                    historyList.add(history);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return historyList;
    }
}
