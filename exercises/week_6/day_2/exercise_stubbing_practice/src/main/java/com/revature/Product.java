package com.revature;

import java.math.BigDecimal;

public class Product {
    private String sku;
    private String category;
    private int stock;
    private BigDecimal price;
    private String name;

    public Product(String category, String name, BigDecimal price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
