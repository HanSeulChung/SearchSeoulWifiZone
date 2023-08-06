package com.example.mission1.wifi;

import com.example.mission1.history.History;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                preparedStatement.setDouble(14, wifiInfo.getLat());
                preparedStatement.setDouble(15, wifiInfo.getLnt());
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
    public void setNullDistance() {
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(dbUrl)){
                String updateSql = " UPDATE WIFI_INFO SET DISTANCE = NULL;";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setNearlyDistance(double lat, double lnt) {
        this.setNullDistance();
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(dbUrl)){
                String updateSql = "UPDATE WIFI_INFO SET DISTANCE=((LAT - ?) * (LAT - ?)) + ((LNT - ?) * (LNT - ?))" +
                        "WHERE MANAGE_NO IN (SELECT MANAGE_NO FROM WIFI_INFO ORDER BY ((LAT - ?) * (LAT - ?) + (LNT - ?) * (LNT -  ?)) ASC LIMIT 20);";
                PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setDouble(1, lat);
                preparedStatement.setDouble(2, lat);
                preparedStatement.setDouble(3, lnt);
                preparedStatement.setDouble(4, lnt);
                preparedStatement.setDouble(5, lat);
                preparedStatement.setDouble(6, lat);
                preparedStatement.setDouble(7, lnt);
                preparedStatement.setDouble(8, lnt);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Wifi> getNearlyWifi(double lat, double lnt) {
        List<Wifi> nearlywifiList = new ArrayList<>();
        try{
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(dbUrl)){
                String selectSql = "SELECT * FROM WIFI_INFO WHERE DISTANCE IS NOT NULL;";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Wifi wifiInfo = new Wifi();
                    wifiInfo.setDistance(resultSet.getDouble("distance"));
                    wifiInfo.setManageNo(resultSet.getString("MANAGE_NO"));
                    wifiInfo.setBorough(resultSet.getString("BOROUGH"));
                    wifiInfo.setWifiName(resultSet.getString("WIFI_NAME"));
                    wifiInfo.setRoadAddr(resultSet.getString("ROAD_ADDR"));
                    wifiInfo.setDetailAddr(resultSet.getString("DETAIL_ADDR"));
                    wifiInfo.setIntallLoc(resultSet.getString("INSTALL_LOC"));
                    wifiInfo.setInstallType(resultSet.getString("INSTALL_TYPE"));
                    wifiInfo.setInstallAgency(resultSet.getString("INSTALL_AGENCY"));
                    wifiInfo.setServiceClassify(resultSet.getString("SERVICE_CLASSFIY"));
                    wifiInfo.setNetType(resultSet.getString("NET_TYPE"));
                    wifiInfo.setInstallYear(resultSet.getInt("INSTALL_YEAR"));
                    wifiInfo.setInOrout(resultSet.getString("IN_OR_OUT"));
                    wifiInfo.setWifiConEnv(resultSet.getString("WIFI_CON_ENV"));
                    wifiInfo.setLat(resultSet.getDouble("LAT"));
                    wifiInfo.setLnt(resultSet.getDouble("LNT"));
                    wifiInfo.setWorkDate(resultSet.getString("WORK_DATE"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nearlywifiList;
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
