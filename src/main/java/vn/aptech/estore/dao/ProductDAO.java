package vn.aptech.estore.dao;

import java.sql.SQLException;
import java.util.List;

import vn.aptech.estore.entities.Product;

public interface ProductDAO extends BaseDao<Product, Integer> {

    public List<Product> findAllByCategoryId(Integer categoryId) throws SQLException;

}
