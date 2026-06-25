package com.jdbcdemo.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jdbcdemo.dao.model.Product;

// DAO Interface
// Defines database operations without specifying how they are implemented
public interface ProductDAO {

    // create
    long insert(Product product) throws SQLException;

    // read
    Optional<Product> findBySKU(String sku) throws Exception;

    // update
    void updatePrice(String sku, double newPrice) throws SQLException;

    // delete
    void deleteBySku(String sku) throws SQLException;

    // read all
    List<Product> findAll() throws SQLException;
}
