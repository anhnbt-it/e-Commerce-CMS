/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.dao;

import java.sql.SQLException;
import java.util.Optional;
import vn.aptech.estore.entities.Admin;
import vn.aptech.estore.entities.Guest;

/**
 *
 * @author anhnb
 */
public interface UserDAO {
    
    Optional<Guest> findFirstByUsernameAndPassword(String username, String password) throws SQLException;
    
    Optional<Admin> findFirstByUserNameAndPassword(String username, String password) throws SQLException;
    
}
