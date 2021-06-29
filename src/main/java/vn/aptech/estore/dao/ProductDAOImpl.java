package vn.aptech.estore.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.utilities.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import vn.aptech.estore.entities.Brand;
import vn.aptech.estore.entities.Category;

public class ProductDAOImpl implements ProductDAO {

    private final Logger LOGGER = LogManager.getLogger(ProductDAOImpl.class);

    private static final String SQL_INSERT = "INSERT INTO tbl_products (category_id, supplier_id, brand_id, name, price, thumbnail_url, description, quantity, status, discount, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "SELECT * FROM tbl_products WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM tbl_products WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM tbl_products ORDER BY id DESC";
    private static final String SQL_SELECT_ONE = "SELECT * FROM tbl_products WHERE id = ?";
    private static final String SQL_SELECT_ALL_BY_CATEGORY_ID = "SELECT * FROM tbl_products WHERE category_id = ? ORDER BY id DESC";
    private static final String SQL_EXISTS_BY_ID = "SELECT count(*) FROM tbl_products WHERE id = ?";
    private static final String SQL_COUNT_ALL = "SELECT count(1) FROM tbl_products";
    
    private final CategoryDAO categoryDAO;
    private final SupplierDao supplierDao;

    public ProductDAOImpl() {
        categoryDAO = new CategoryDAOImpl();
        supplierDao = new SupplierDaoImpl();
    }
    
    @Override
    public Optional<Product> saveOrUpdate(Product entity) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rowsAffected = -1;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            if (entity.getId() != null) {
//                String sql = "UPDATE tbl_products SET ";
                throw new UnsupportedOperationException("Method chua hoan thien!");
            } else {
                pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, entity.getCategory().getId());
                pstmt.setInt(2, entity.getSupplier().getId());
                pstmt.setInt(3, entity.getBrand().getId());
                pstmt.setNString(4, entity.getName());
                pstmt.setDouble(5, entity.getUnitPrice());
                pstmt.setString(6, entity.getThumbnailUrl());
                pstmt.setNString(7, entity.getDescription());
                pstmt.setInt(8, entity.getQuantity());
                pstmt.setString(9, entity.getStatus());
                pstmt.setDouble(10, entity.getDiscount());
                pstmt.setTimestamp(11, entity.getModifiedDate());
                rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        product = this.mapRersultSetToObject(rs);
                    }
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            if (conn != null) conn.rollback();
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
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
                product = this.mapRersultSetToObject(rs);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return Optional.ofNullable(product);
    }

    @Override
    public boolean existsById(Integer id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rowsAffected = -1;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_EXISTS_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rowsAffected = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return rowsAffected > 0;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = pstmt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                Product product = this.mapRersultSetToObject(rs);
                products.add(product);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return products;
    }

    @Override
    public long count() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_COUNT_ALL);
            if (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return count;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        Connection conn;
        PreparedStatement pstmt;
        int rowsAffected = -1;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return rowsAffected > 0;
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
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT_ALL_BY_CATEGORY_ID);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                Product product = this.mapRersultSetToObject(rs);
                products.add(product);
            }
            pstmt.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) pstmt.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return products;
    }

    @Override
    public Product mapRersultSetToObject(ResultSet rs) throws SQLException {
        Product product = new Product();
        try {
            product.setId(rs.getInt("id"));
            product.setCreatedBy(rs.getInt("created_by"));
            product.setModifiedBy(rs.getInt("modified_by"));
            product.setCreatedDate(rs.getTimestamp("created_date"));
            product.setModifiedDate(rs.getTimestamp("modified_date"));
            product.setCategory(categoryDAO.findById(rs.getInt("category_id")).orElse(null));
            product.setSupplier(supplierDao.findById(rs.getInt("supplier_id")).orElse(null));
//            product.setBrand(brand);
            product.setName(rs.getString("name"));
            product.setQuantity(rs.getInt("quantity"));
            product.setUnitPrice(rs.getDouble("unit_price"));
            product.setUnitPrice(rs.getDouble("thumbnail_url"));
            product.setUnitPrice(rs.getDouble("description"));
            product.setUnitPrice(rs.getDouble("quantity"));
            product.setUnitPrice(rs.getDouble("status"));
            product.setUnitPrice(rs.getDouble("discount"));
        } catch (SQLException ex) {
            throw ex;
        }
        return product;
    }
}
