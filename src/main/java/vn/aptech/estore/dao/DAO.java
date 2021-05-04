package vn.aptech.estore.dao;

import vn.aptech.estore.entities.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T, ID> {

    Optional<T> saveOrUpdate(T entity) throws SQLException;

    Optional<T> findById(ID id) throws SQLException;

    boolean existsById(ID id);

    List<T> findAll() throws SQLException;

    long count();

    boolean deleteById(ID id) throws SQLException;

    void delete(T entity);
}
