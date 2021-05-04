/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author anhnbt
 */
public class DBConnection {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=ecommerce_db;user=sa;password=KhoaiTay@2019";

    public static Connection getConnection() throws SQLException {
        Connection conn;
        //            System.out.println("Connecting to SQL Server ...");
        conn = DriverManager.getConnection(CONNECTION_URL);
//            System.out.println("Connected to database.");
        return conn;
    }

}
