/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vn.aptech.estore.utilities.DBConnection;

/**
 *
 * @author anhnbt
 */
public class AuthServiceImpl implements AuthService {
    
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean isAuthenticated(String username, String password) throws SQLException {
        
    }
    
}
