/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.ecommerce.service;

import vn.aptech.ecommerce.dao.ProductDAO;
import vn.aptech.ecommerce.dao.ProductDAOImpl;
import vn.aptech.ecommerce.entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class ProductServiceImpl implements ProductService {
    
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl();
    }

    @Override
    public int saveOrUpdate(Product entity) throws SQLException {
        return this.productDAO.saveOrUpdate(entity);
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return this.productDAO.findAll();
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        return this.productDAO.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Integer id) throws SQLException {
        return this.productDAO.findById(id);
    }

    @Override
    public List<Product> findAllByCategoryId(Integer categoryId) throws SQLException {
        return this.productDAO.findAllByCategoryId(categoryId);
    }

    @Override
    public boolean existById(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
