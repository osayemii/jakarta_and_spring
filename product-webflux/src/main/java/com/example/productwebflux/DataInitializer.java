package com.example.productwebflux;

import com.example.productwebflux.model.Product;
import com.example.productwebflux.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

/**
 * Seeds the database with the 5 products from Table 4.3 (pg 159) on first run.
 * Skips seeding if data already exists.
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedProducts(ProductRepository repo) {
        return args -> {
            if (repo.count() > 0) return; // already seeded

            List<Product> products = List.of(
                createProduct("P_ID_1", "Laptop",      5,  50000.0, "Electronics", "ABC Company",       "2025-12-30"),
                createProduct("P_ID_2", "Smartphone",  6,  13000.0, "Electronics", "XYZ Inc",           "2026-08-15"),
                createProduct("P_ID_3", "Head Phones", 10,  1000.0, "Electronics", "PQR Ltd",           "2024-12-10"),
                createProduct("P_ID_4", "Tablet",      6,   5000.0, "Electronics", "MNO Corporation",   "2025-10-14"),
                createProduct("P_ID_5", "Smart Watch", 20,   800.0, "Electronics", "JKL Enterprises",   "2026-06-16")
            );

            repo.saveAll(products);
            System.out.println(">>> Seeded " + products.size() + " products from Table 4.3");
        };
    }

    private Product createProduct(String id, String name, int qty, double price,
                                   String category, String manufacturer, String expiry) {
        Product p = new Product();
        p.setProductId(id);
        p.setProductName(name);
        p.setQuantity(qty);
        p.setPrice(price);
        p.setCategory(category);
        p.setManufacturer(manufacturer);
        p.setExpiryDate(LocalDate.parse(expiry));
        return p;
    }
}
