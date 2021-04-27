/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.estore.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author anhnbt
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID> {
    
    public int saveOrUpdate(T entity) throws SQLException;
    
    public List<T> findAll() throws SQLException;
    
    public boolean deleteById(ID id) throws SQLException;
    
    public Optional<T> findById(ID id) throws SQLException;
    
    boolean existById(ID id) throws SQLException;
    
}
