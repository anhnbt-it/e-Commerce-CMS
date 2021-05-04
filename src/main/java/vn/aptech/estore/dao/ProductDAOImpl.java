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

    private static final String SQL_INSERT = "INSERT INTO tbl_products (category_id, supplier_id, brand_id, name, price, thumbnail_url, description, quantity, status, discount, view_count, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "SELECT * FROM tbl_products WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_products WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM tbl_products ORDER BY id DESC";
    private static final String SQL_SELECT_ONE = "SELECT * FROM tbl_products WHERE id = ?";
    private static final String SQL_SELECT_ALL_BY_CATEGORY_ID = "SELECT * FROM tbl_products WHERE category_id = ? ORDER BY id DESC";
    private static final String SQL_EXISTS_BY_ID = "SELECT count(*) FROM tbl_products WHERE id = ?";
    private static final String SQL_COUNT_ALL = "SELECT count(1) FROM tbl_products";

    @Override
    public Optional<Product> saveOrUpdate(Product entity) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            if (entity.getId() != null) {
//                String sql = "UPDATE tbl_products SET ";
                throw new UnsupportedOperationException("Method chua hoan thien!");
            } else {
                pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, entity.getCategoryId());
                pstmt.setInt(2, entity.getSupplierId());
                pstmt.setInt(3, entity.getBrandId());
                pstmt.setNString(4, entity.getName());
                pstmt.setDouble(5, entity.getUnitPrice());
                pstmt.setString(6, entity.getThumbnailUrl());
                pstmt.setNString(7, entity.getDescription());
                pstmt.setInt(8, entity.getQuantity());
                pstmt.setString(9, entity.getStatus());
                pstmt.setInt(10, entity.getDiscount());
                pstmt.setInt(11, entity.getViewCount());
                pstmt.setTimestamp(12, entity.getModifiedDate());
                count = pstmt.executeUpdate();
                if (count > 0) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        product = new Product();
                        // to do
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error("saveOrUpdate exception", e);
            if (conn != null) conn.rollback();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.setAutoCommit(true);
        }
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> findById(Integer productId) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT_ONE);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product();
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
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
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
            pstmt = conn.prepareStatement(SQL_EXISTS_BY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
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
            pstmt = conn.prepareStatement(SQL_SELECT_ALL);
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
                product.setModifiedDate(rs.getTimestamp("updated_at"));
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
            stmt = conn.createStatement();
            try (ResultSet rs = stmt.executeQuery(SQL_COUNT_ALL)) {
                if (rs.next()) {
                    count = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("count", e);
        }
        return count;
    }

    @Override
    public boolean deleteById(Integer id) {
        Connection conn;
        PreparedStatement pstmt;
        int count = -1;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_DELETE);
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
            pstmt = conn.prepareStatement(SQL_SELECT_ALL_BY_CATEGORY_ID);
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
                product.setModifiedDate(rs.getTimestamp("updated_at"));
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
