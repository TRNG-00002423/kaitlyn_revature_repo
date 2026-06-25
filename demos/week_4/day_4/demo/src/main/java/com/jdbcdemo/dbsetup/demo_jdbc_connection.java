package com.jdbcdemo.dbsetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class demo_jdbc_connection {

    // JDBC conection string.
    // SQLite will create a database file if it does not exist
    private static final String URL = "jdbc:sqlite:week4_jdbc_demo.db";

    public static void main(String[] args) throws SQLException {
        // Open a database connection
        // try-with-resources automatically closes the connection
        try (Connection conn = DriverManager.getConnection(URL)) {

            // Create a fresh customer table
            bootstrapSchema(conn);
        }
    }

    private static void bootstrapSchema(Connection conn) throws SQLException {
        // Statement is typically used for static SQL
        try (Statement st = conn.createStatement()) {

            // Remove table if already exists
            st.executeUpdate("DROP TABLE IF EXISTS customer");

            // Create a new customer table.
            st.executeUpdate("""
                    CREATE TABLE customer (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        email TEXT NOT NULL UNIQUE,
                        name TEXT NOT NULL
                    )""");
        }
    }

}