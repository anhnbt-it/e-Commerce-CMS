/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.ecommerce.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import vn.aptech.ecommerce.dao.CategoryDAO;
import vn.aptech.ecommerce.dao.CategoryDAOImpl;
import vn.aptech.ecommerce.entities.Category;


public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public CategoryServiceImpl() {
        this.categoryDAO = new CategoryDAOImpl();
    }

    @Override
    public int saveOrUpdate(Category category) throws SQLException {
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
    
}
