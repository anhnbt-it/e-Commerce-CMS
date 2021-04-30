/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import vn.aptech.estore.entities.Admin;
import vn.aptech.estore.entities.Customer;
import vn.aptech.estore.utilities.DBConnection;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public Optional<Customer> findFirstByUsernameAndPassword(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Customer customer = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM tbl_customers WHERE username = ? AND password = ?";
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setUsername(rs.getString("username"));
                customer.setEmail(rs.getString("email"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setDateOfBirth(rs.getDate("dateOfBirth"));
            }
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
        return Optional.of(customer);
    }

    @Override
    public Optional<Admin> findFirstByUserNameAndPassword(String username, String password) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        Connection conn = null;
        List<Customer> customers = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            cs = conn.prepareCall("{call SelectAllCustomers()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
            }
        } catch (SQLException ex) {
            if (conn != null) conn.rollback();
            throw ex;
        }
        return customers;
    }
    
}
