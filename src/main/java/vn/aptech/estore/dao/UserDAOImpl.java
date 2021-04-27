/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import vn.aptech.estore.entities.Admin;
import vn.aptech.estore.entities.Guest;
import vn.aptech.estore.utilities.DBConnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public Optional<Guest> findFirstByUsernameAndPassword(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt;
        Guest guest = null;
        try {
            String sql = "SELECT * FROM tbl_users WHERE username = ? AND password = ?";
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                guest = new Guest();
                guest.setUsername(rs.getString("username"));
            }
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
        return Optional.of(guest);
    }

    @Override
    public Optional<Admin> findFirstByUserNameAndPassword(String username, String password) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
