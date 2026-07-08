package com.revature;

import java.util.ArrayList;
import java.util.List;

/**
 * Order
 */
public class Order {

    private List<String> orderLines;

    Order() {
        this.orderLines = new ArrayList<>();
    }

    void addLine(String productName, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.orderLines.add(productName);
        }

    }

    List<String> getProducts() {
        return this.orderLines;
    }

}
