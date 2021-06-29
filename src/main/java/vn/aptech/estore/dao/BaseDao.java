package vn.aptech.estore.dao;

import java.sql.ResultSet;
import vn.aptech.estore.entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T, ID> {

    Optional<T> saveOrUpdate(T entity) throws SQLException;

    Optional<T> findById(ID id) throws SQLException;

    boolean existsById(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    long count() throws SQLException;

    boolean deleteById(ID id) throws SQLException;

    void delete(T entity) throws SQLException;
    
    T mapRersultSetToObject(ResultSet rs) throws SQLException;
}
