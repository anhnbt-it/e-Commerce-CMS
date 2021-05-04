package vn.aptech.estore.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import vn.aptech.estore.dao.CategoryDAO;
import vn.aptech.estore.dao.CategoryDAOImpl;
import vn.aptech.estore.entities.Category;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public CategoryServiceImpl() {
        this.categoryDAO = new CategoryDAOImpl();
    }

    @Override
    public Optional<Category> saveOrUpdate(Category category) throws SQLException {
        return categoryDAO.saveOrUpdate(category);
    }

    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDAO.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        return categoryDAO.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Integer id) throws SQLException {
        return categoryDAO.findById(id);
    }

    @Override
    public boolean existById(Integer id) throws SQLException {
        return categoryDAO.existsById(id);
    }

}
