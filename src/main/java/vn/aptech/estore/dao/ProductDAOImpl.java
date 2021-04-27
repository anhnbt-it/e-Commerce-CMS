package vn.aptech.estore.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.utilities.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl implements ProductDAO {

    private final Logger LOGGER = LogManager.getLogger(ProductDAOImpl.class);

    @Override
    public int saveOrUpdate(Product entity) throws SQLException {
        int rowCount = -1;
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            if (entity.getId() != null) {
//                String sql = "UPDATE tbl_products SET ";
                throw new UnsupportedOperationException("Method chua hoan thien!");
            } else {
                String sql = "INSERT INTO tbl_products (category_id, supplier_id, brand_id, name, price, thumbnail_url, "
                        + "description, quantity, status, discount, view_count, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
                pstmt.setInt(1, entity.getCategoryId());
                pstmt.setInt(2, entity.getSupplierId());
                pstmt.setInt(3, entity.getBrandId());
                pstmt.setString(4, entity.getName());
                pstmt.setDouble(5, entity.getUnitPrice());
                pstmt.setString(6, entity.getThumbnailUrl());
                pstmt.setString(7, entity.getDescription());
                pstmt.setInt(8, entity.getQuantity());
                pstmt.setString(9, entity.getStatus());
                pstmt.setInt(10, entity.getDiscount());
                pstmt.setInt(11, entity.getViewCount());
                pstmt.setTimestamp(12, entity.getUpdatedAt());
            }
            rowCount = pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("ProductDAOImpl: Da co loi xay ra: " + e.getMessage());
            LOGGER.error(e);
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
        return rowCount;
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        Product product = null;
        Connection conn;
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM tbl_products WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            product = new Product();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setBrandId(rs.getInt("brand_id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUnitPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getString("status"));
            }
        } catch (SQLException ex) {
            System.err.println("ProductDAOImpl: Da co loi xay ra" + ex.getMessage());
        }
        return Optional.ofNullable(product);
    }

    @Override
    public boolean existsById(Integer id) {
        Connection conn;
        PreparedStatement pstmt;
        int count = -1;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT count(*) FROM tbl_products WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch(Exception e) {
            LOGGER.error(e);
        }
        return count > 0;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = null;
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM tbl_products ORDER BY id DESC");
            ResultSet rs = pstmt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setSupplierId(rs.getInt("supplier_id"));
                product.setBrandId(rs.getInt("brand_id"));
                product.setName(rs.getString("name"));
                product.setUnitPrice(rs.getDouble("price"));
                product.setUnitPrice(rs.getDouble("price"));
                product.setThumbnailUrl(rs.getString("thumbnail_url"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setStatus(rs.getString("status"));
                product.setDiscount(rs.getInt("discount"));
                product.setViewCount(rs.getInt("view_count"));
                product.setUpdatedAt(rs.getTimestamp("updated_at"));
                products.add(product);
            }
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
        return products;
    }

    @Override
    public long count() {
        Connection conn;
        Statement stmt;
        long count = 0;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT count(*) as totalElements FROM tbl_products";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getLong("totalElements");
            }
        } catch(SQLException e) {
            LOGGER.error(e);
        }
        return count;
    }

    @Override
    public boolean deleteById(Integer id) {
        Connection conn;
        PreparedStatement pstmt;
        int count = -1;
        try {
            String sql = "DELETE FROM tbl_products WHERE id = ?";
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return count > 0;
    }

    @Override
    public void delete(Product entity) {

    }

    /**
     * @param categoryId
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> findAllByCategoryId(Integer categoryId) throws SQLException {
        List<Product> products = null;
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM tbl_products WHERE category_id = ? ORDER BY id DESC");
            pstmt.setInt(1, categoryId);
            ResultSet rs = pstmt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setSupplierId(rs.getInt("supplier_id"));
                product.setBrandId(rs.getInt("brand_id"));
                product.setName(rs.getString("name"));
                product.setUnitPrice(rs.getDouble("price"));
                product.setThumbnailUrl(rs.getString("thumbnail_url"));
                product.setDescription(rs.getString("description"));
                product.setQuantity(rs.getInt("quantity"));
                product.setStatus(rs.getString("status"));
                product.setDiscount(rs.getInt("discount"));
                product.setViewCount(rs.getInt("view_count"));
                product.setUpdatedAt(rs.getTimestamp("updated_at"));
                products.add(product);
            }
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
        return products;
    }
}
