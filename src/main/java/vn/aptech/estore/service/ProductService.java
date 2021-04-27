/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.estore.entities.Product;

/**
 *
 * @author anhnbt
 */
public interface ProductService extends BaseService<Product, Integer> {

    List<Product> findAllByCategoryId(Integer categoryId) throws SQLException;
    
}
