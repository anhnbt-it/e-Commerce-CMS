package vn.aptech.ecommerce.dao;

import java.sql.SQLException;
import java.util.List;

import vn.aptech.ecommerce.entities.Product;

public interface ProductDAO extends DAO<Product, Integer> {

    public List<Product> findAllByCategoryId(Integer categoryId) throws SQLException;

}
