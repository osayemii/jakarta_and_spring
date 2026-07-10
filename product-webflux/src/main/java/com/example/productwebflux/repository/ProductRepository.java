package com.example.productwebflux.repository;

import com.example.productwebflux.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // JpaRepository provides: findAll(), findById(), save(), deleteById()
}
