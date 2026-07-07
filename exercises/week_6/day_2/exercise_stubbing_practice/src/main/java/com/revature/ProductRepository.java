package com.revature;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String sku);

    List<Product> findByCategory(String category);
}
