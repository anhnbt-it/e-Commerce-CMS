package vn.aptech.ecommerce.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.ecommerce.entities.Category;
import vn.aptech.utilities.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger LOGGER = LogManager.getLogger(CategoryDAOImpl.class);

    @Override
    public int saveOrUpdate(Category entity) throws SQLException {
        int rowCount = -1;
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            if (entity.getId() != null) {
                String sql = "UPDATE tbl_categories SET name = ?, updated_at = ? WHERE id = ?";
                pstmt = conn.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
                pstmt.setString(1, entity.getName());
                pstmt.setTimestamp(2, entity.getUpdatedAt());
                pstmt.setInt(3, entity.getId());
            } else {
                String sql = "INSERT INTO tbl_categories (name) VALUES (?)";
                pstmt = conn.prepareStatement(sql, Statement.NO_GENERATED_KEYS);
                pstmt.setString(1, entity.getName());
            }
            rowCount = pstmt.executeUpdate();
            conn.commit();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println("Da xay ra loi CSDL: " + ex.getMessage());
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
        return rowCount;
    }

    @Override
    public Optional<Category> findById(Integer id) throws SQLException {
        Connection conn = null;
        Category category = null;
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM tbl_categories WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
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
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("SELECT * FROM tbl_categories ORDER BY id DESC");
            ResultSet rs = pstmt.executeQuery();
            categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
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
        PreparedStatement pstmt;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("DELETE FROM tbl_categories WHERE id = ?");
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            conn.commit();
            pstmt.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            conn.rollback();
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public void delete(Category entity) {

    }
}
