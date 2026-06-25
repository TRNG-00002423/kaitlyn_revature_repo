package com.jdbcdemo.dbsetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class demo_prepared_statements {
    private static final String URL = "jdbc:sqlite:week4_jdbc_prep_demo.db";

    public static void main(String[] args) throws SQLException {
        String userInput = "alice";
        userInput = "' OR '1'='1";

        // open database connection

        try (Connection conn = DriverManager.getConnection(URL)) {
            try (Statement st = conn.createStatement()) {
                st.executeUpdate("DROP TABLE IF EXISTS user_account");

                st.executeUpdate("""
                        CREATE TABLE user_account (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            secret TEXT NOT NULL
                        )
                        """);

                st.executeUpdate("""
                            INSERT INTO user_account (name, secret) VALUES
                            ('alice', 'pw1'), ('bob', 'pw2')
                        """);
            }
            // example 1: unsafe statement
            System.out.println("Unsafe concatenation");
            // User input is directly inserted into SQL text
            String unsafeSQL = "SELECT name, secret FROM user_account WHERE name = '" + userInput + "'";
            System.out.println("SQL: " + unsafeSQL);
            try (Statement st = conn.createStatement()) {
                ResultSet rs = st.executeQuery(unsafeSQL);
                while (rs.next()) {
                    System.out.printf(
                            "matched: %s / %s%n",
                            rs.getString(1),
                            rs.getString(2));
                }
            }

            // ? is a parameter placeholder
            String safeSQL = "SELECT name, secret FROM user_account WHERE name = ?";
            try (PreparedStatement ps = conn.prepareStatement(safeSQL)) {
                ps.setString(1, userInput);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.printf(
                                "matched: %s / %s%n",
                                rs.getString(1),
                                rs.getString(2));
                    }
                }
            }
        }
    }
}