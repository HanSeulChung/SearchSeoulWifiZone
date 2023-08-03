package com.example.mission1.history;

import com.example.mission1.wifi.Wifi;

import java.sql.*;

public class HisoryRepository {
    private String dbUrl = "jdbc:sqlite:C:/Users/w0w12/Java/mission/mission1/src/main/java/db/seoulWifi.db";
    public void createHistoryTable() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(dbUrl);
                 Statement statement = connection.createStatement()) {
                String createTableSql = "CREATE TABLE IF NOT EXISTS LOCATION_HISORY (" +
                        "ID  INT PRIMARY KEY AUTO_INCREMENT," +
                        "LAT DOUBLE," +
                        "LNT DOUBLE," +
                        "INQUIRY_DATE DATE," +
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
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(dbUrl)) {
                String insertSql = "INSERT INTO LOCATION_HISORY (LAT, LNT, INQUIRY_DATE) VALUES (?, ?, ?);";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                    preparedStatement.setDouble(1, history.getLat());
                    preparedStatement.setDouble(2, history.getLnt());
                    preparedStatement.setDate(3, history.getInquiryDate());

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver 찾지 못하였습니다.");
            e.printStackTrace();
        }
    }
}
