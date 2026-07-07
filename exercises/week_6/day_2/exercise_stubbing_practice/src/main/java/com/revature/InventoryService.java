package com.revature;

public interface InventoryService {
    boolean checkStock(String sku, int quantity);

    void reserveStock(String sku, int quantity); // void

    void releaseStock(String sku, int quantity); // void
}
