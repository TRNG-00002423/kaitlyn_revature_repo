package com.jdbcdemo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jdbcdemo.dao.model.Product;

public class JDBCProductDAO implements ProductDAO {

    private Connection connection;

    JDBCProductDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public void deleteBySku(String sku) throws SQLException {
        String sql = "DELETE FROM product WHERE sku = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sku);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Product> findAll() throws SQLException {
        String sql = "SELECT id, sku, name, price FROM product ORDER BY id";
        List<Product> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(mapRow(rs));
            }
        }
        return out;
    }

    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"),
                rs.getString("sku"),
                rs.getString("name"),
                rs.getDouble("price"));
    }

    @Override
    public Optional<Product> findBySKU(String sku) throws Exception {
        String sql = "SELECT id, name, sku, price FROM product WHERE sku = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sku);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public long insert(Product product) throws SQLException {
        String sql = "INSERT INTO product (sku, name, price) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getSku());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            } catch (IllegalStateException k) {
                k.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public void updatePrice(String sku, double newPrice) throws SQLException {
        String sql = "UPDATE product SET price = ? WHERE sku = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, sku);
            int n = ps.executeUpdate();
        }

    }

}
