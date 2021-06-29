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
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.estore.entities.Supplier;
import vn.aptech.estore.utilities.DBConnection;


public class SupplierDaoImpl implements SupplierDao {
    private static final String SQL_SELECT_ONE = "SELECT * FROM tbl_suppliers WHERE id = ?";

    @Override
    public Optional<Supplier> saveOrUpdate(Supplier entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Supplier> findById(Integer id) throws SQLException {
        Supplier supplier = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT_ONE);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                supplier = this.mapRersultSetToObject(rs);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return Optional.ofNullable(supplier);
    }

    @Override
    public boolean existsById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Supplier> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Supplier entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Supplier mapRersultSetToObject(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        try {
            supplier.setId(rs.getInt("id"));
            supplier.setCreatedBy(rs.getInt("created_by"));
            supplier.setModifiedBy(rs.getInt("modified_by"));
            supplier.setCreatedDate(rs.getTimestamp("created_date"));
            supplier.setModifiedDate(rs.getTimestamp("modified_date"));
            supplier.setName(rs.getString("name"));
            supplier.setThumbnailUrl(rs.getString("thumbnail_url"));
            supplier.setCity(rs.getString("city"));
            supplier.setContactNumber(rs.getString("contact_number"));
        } catch (SQLException ex) {
            throw ex;
        }
        return supplier;
    }
    
}
