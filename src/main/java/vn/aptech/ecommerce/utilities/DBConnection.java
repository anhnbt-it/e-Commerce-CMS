/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anhnbt
 */
public class DBConnection {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=quanlysanpham;user=sa;password=KhoaiTay@2019";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
//            System.out.println("Connecting to SQL Server ...");
            conn = DriverManager.getConnection(CONNECTION_URL);
//            System.out.println("Connected to database.");
        } catch (SQLException e) {
            System.out.println("Could not connect: " + e.getMessage());
        }
        return conn;
    }
    
}
