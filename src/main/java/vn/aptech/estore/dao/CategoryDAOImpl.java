package vn.aptech.estore.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.utilities.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger LOGGER = LogManager.getLogger(CategoryDAOImpl.class);
    public static final String SQL_UPDATE = "UPDATE tbl_categories SET name = ?, updated_at = ? WHERE id = ?";
    public static final String SQL_INSERT = "INSERT INTO tbl_categories (name) VALUES (?)";
    public static final String SQL_SELECT_ONE = "SELECT * FROM tbl_categories WHERE id = ?";
    public static final String SQL_SELECT_ALL = "SELECT * FROM tbl_categories ORDER BY id DESC";
    public static final String SQL_DELETE = "DELETE FROM tbl_categories WHERE id = ?";

    @Override
    public Optional<Category> saveOrUpdate(Category category) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            if (category.getId() != null) {
                pstmt = conn.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, category.getName());
                pstmt.setTimestamp(2, category.getModifiedDate());
                pstmt.setInt(3, category.getId());
            } else {
                pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, category.getName());
            }
            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    // To do
                }
            }
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error("saveOrUpdate exception: ", e);
            if (conn != null) conn.rollback();
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        return Optional.ofNullable(category);
    }

    @Override
    public Optional<Category> findById(Integer id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Category category = null;
        try {
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT_ONE);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error("findById exception: ", e);
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }
        return Optional.ofNullable(category);
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = null;
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
                ResultSet rs = pstmt.executeQuery()
        ) {
            categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            LOGGER.error("findAll exception: ", e);
        }
        return categories;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            conn.commit();
        } catch (SQLException e) {
            LOGGER.error("deleteById exception", e);
            if (conn != null) conn.rollback();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        return false;
    }

    @Override
    public void delete(Category entity) {

    }
}
