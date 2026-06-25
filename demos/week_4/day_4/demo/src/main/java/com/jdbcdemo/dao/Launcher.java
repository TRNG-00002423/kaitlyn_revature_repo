package com.jdbcdemo.dao;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import com.jdbcdemo.dao.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;

public class Launcher {
    public static void main(String[] args) throws Exception {
        String URL = "jdbc:sqlite:week4:jdbc_dao_demo.db";

        // open database connection
        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DROP TABLE IF EXISTS product");
                st.executeUpdate("""
                        CREATE TABLE product (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            sku TEXT NOT NULL UNIQUE,
                            name TEXT NOT NULL,
                            price REAL NOT NULL
                        )
                        """);
            }
            ProductDAO dao = new JDBCProductDAO(conn);
            Product p = new Product(0, "SKU-1", "Mug", 12.5);
            Product p2 = new Product(1, "SKU-2", "Not a Mug", 13.5);
            long id = dao.insert(p);
            System.out.println("inserted id = " + id);

            List<Product> products = dao.findAll();
            for (Product p1 : products) {
                System.out.println(p1);
            }

            System.out.println(dao.findBySKU("SKU-1"));

            Optional<Product> loaded = dao.findBySKU("SKU-1");
        }
    }
}
