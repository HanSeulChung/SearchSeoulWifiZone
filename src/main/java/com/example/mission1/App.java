package com.example.mission1;

import com.example.mission1.database.SQLiteManager;

import java.sql.SQLException;


public class App {

    public static void main(String[] args) throws SQLException {
        SQLiteManager manager = new SQLiteManager();

        manager.createConnection();     // 연결

    }
}