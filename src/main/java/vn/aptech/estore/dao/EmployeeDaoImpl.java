/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.dao;

import vn.aptech.estore.entities.Employee;
import vn.aptech.estore.utilities.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Optional<Employee> findFirstByUsernameAndPassword(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Employee employee = null;
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
                employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setUsername(rs.getString("username"));
                employee.setEmail(rs.getString("email"));
                employee.setFirstName(rs.getString("name"));
                employee.setPhone(rs.getString("phone"));
                employee.setDateOfBirth(rs.getDate("dateOfBirth"));
            }
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.setAutoCommit(true);
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public Optional<Employee> findFirstByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> saveOrUpdate(Employee entity) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        Connection conn = null;
        List<Employee> employees = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            cs = conn.prepareCall("{call SelectAllCustomers()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                employees = new ArrayList<>();
                Employee employee = new Employee();
                employees.add(employee);
            }
        } catch (SQLException ex) {
            if (conn != null) conn.rollback();
            throw ex;
        }
        return employees;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean deleteById(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public void delete(Employee entity) {

    }

}
