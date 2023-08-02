package com.example.mission1.wifi;

import java.sql.*;

public class WifiRepository {
    //private String dbUrl = "jdbc:sqlite:C:/Users/w0w12/Java/mission/mission1/src/main/db/seoulWifi.db"; //
    private String dbUrl = "jdbc:sqlite:C:/Users/w0w12/Java/mission/mission1/src/main/java/db/seoulWifi.db";
    public void createWifiTable() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(dbUrl);
                 Statement statement = connection.createStatement()) {
                String createTableSql = "CREATE TABLE IF NOT EXISTS WIFI_INFO (" +
                        "DISTANCE  REAL," +
                        "MANAGE_NO TEXT INTEGER PRIMARY KEY NOT NULL," +
                        "BOROUGH VARCHAR(10)," +
                        "WIFI_NAME VARCHAR(20)," +
                        "ROAD_ADDR VARCHAR(100)," +
                        "DETAIL_ADDR VARCHAR(20)," +
                        "INSTALL_LOC VARCHAR(20)," +
                        "INSTALL_TYPE VARCHAR(20)," +
                        "INSTALL_AGENCY VARCHAR(20)," +
                        "SERVICE_CLASSFIY VARCHAR(20)," +
                        "NET_TYPE TEXT," +
                        "INSTALL_YEAR INTEGER," +
                        "IN_OR_OUT VARCHAR(20)," +
                        "WIFI_CON_ENV VARCHAR(20)," +
                        "LAT REAL," +
                        "LNT REAL," +
                        "WORK_DATE TEXT" +
                        ");";
                // 테이블 생성 쿼리 실행
                statement.execute(createTableSql);

                System.out.println("WIFI_INFO 테이블이 성공적으로 생성되었습니다.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        }
    }

    public void insertWifiTable(Wifi[] wifiInfoArray) {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
            String insertSql = "INSERT INTO WIFI_INFO " +
                    "(MANAGE_NO, BOROUGH, WIFI_NAME, ROAD_ADDR, DETAIL_ADDR, INSTALL_LOC, INSTALL_TYPE, INSTALL_AGENCY, SERVICE_CLASSFIY, NET_TYPE, INSTALL_YEAR, IN_OR_OUT, WIFI_CON_ENV, LAT, LNT, WORK_DATE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

            // 배치 처리를 위해 Auto Commit 비활성화
            connection.setAutoCommit(false);

            for (Wifi wifiInfo : wifiInfoArray) {
                preparedStatement.setString(1, wifiInfo.getManageNo());
                preparedStatement.setString(2, wifiInfo.getBorough());
                preparedStatement.setString(3, wifiInfo.getWifiName());
                preparedStatement.setString(4, wifiInfo.getRoadAddr());
                preparedStatement.setString(5, wifiInfo.getDetailAddr());
                preparedStatement.setString(6, wifiInfo.getIntallLoc());
                preparedStatement.setString(7, wifiInfo.getInstallType());
                preparedStatement.setString(8, wifiInfo.getInstallAgency());
                preparedStatement.setString(9, wifiInfo.getServiceClassify());
                preparedStatement.setString(10, wifiInfo.getNetType());
                preparedStatement.setInt(11, wifiInfo.getInstallYear());
                preparedStatement.setString(12, wifiInfo.getInOrout());
                preparedStatement.setString(13, wifiInfo.getWifiConEnv());
                preparedStatement.setFloat(14, wifiInfo.getLat());
                preparedStatement.setFloat(15, wifiInfo.getLnt());
                preparedStatement.setString(16, wifiInfo.getWorkDate());

                // 배치에 추가
                preparedStatement.addBatch();
            }

            // 배치 실행
            preparedStatement.executeBatch();

            // 커밋
            connection.commit();

            // Auto Commit 다시 활성화
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDistance(int lat, int lnt) {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAlldata() {
        try (Connection connection = DriverManager.getConnection(dbUrl)) {
        String deleteAllSql = "DELETE FROM WIFI_INFO";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteAllSql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
