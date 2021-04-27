/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.service;

import java.sql.SQLException;

/**
 *
 * @author anhnbt
 */
public interface AuthService {
    
    boolean isAuthenticated(String username, String password) throws SQLException;
    
}
