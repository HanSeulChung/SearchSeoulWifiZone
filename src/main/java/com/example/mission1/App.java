package com.example.mission1;

import java.sql.SQLException;


public class App {

    public static void main(String[] args) throws SQLException {
        SQLiteManager manager = new SQLiteManager();

        manager.createConnection();     // 연결

    }
}