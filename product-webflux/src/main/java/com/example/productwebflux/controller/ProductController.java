package com.example.productwebflux.controller;

import com.example.productwebflux.model.Product;
import com.example.productwebflux.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * Reactive REST Controller using WebFlux.
 *
 * JPA is blocking by nature. We wrap every blocking call in
 * Mono.fromCallable(...).subscribeOn(Schedulers.boundedElastic())
 * so WebFlux can handle them without blocking the event loop.
 *
 * Test all endpoints with Postman at http://localhost:8081
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ── GET all products ──────────────────────────────────────────────
    // GET http://localhost:8081/api/products
    @GetMapping
    public Flux<Product> getAllProducts() {
        return Mono.fromCallable(productRepository::findAll)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable);
    }

    // ── GET single product by ID ──────────────────────────────────────
    // GET http://localhost:8081/api/products/P_ID_1
    @GetMapping("/{productId}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable String productId) {
        return Mono.fromCallable(() -> productRepository.findById(productId))
                .subscribeOn(Schedulers.boundedElastic())
                .map(opt -> opt
                        .map(p -> ResponseEntity.ok(p))
                        .orElse(ResponseEntity.notFound().build()));
    }

    // ── POST create new product ───────────────────────────────────────
    // POST http://localhost:8081/api/products
    // Body (JSON): { "productId":"P_ID_6", "productName":"...", ... }
    @PostMapping
    public Mono<ResponseEntity<Product>> createProduct(@Valid @RequestBody Product product) {
        return Mono.fromCallable(() -> productRepository.save(product))
                .subscribeOn(Schedulers.boundedElastic())
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }

    // ── PUT update existing product ───────────────────────────────────
    // PUT http://localhost:8081/api/products/P_ID_1
    // Body (JSON): full product object with updated fields
    @PutMapping("/{productId}")
    public Mono<ResponseEntity<Product>> updateProduct(@PathVariable String productId,
                                                        @Valid @RequestBody Product product) {
        return Mono.fromCallable(() -> productRepository.findById(productId))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(opt -> {
                    if (opt.isEmpty()) {
                        return Mono.just(ResponseEntity.<Product>notFound().build());
                    }
                    product.setProductId(productId);
                    return Mono.fromCallable(() -> productRepository.save(product))
                            .subscribeOn(Schedulers.boundedElastic())
                            .map(updated -> ResponseEntity.ok(updated));
                });
    }

    // ── DELETE product ────────────────────────────────────────────────
    // DELETE http://localhost:8081/api/products/P_ID_1
    @DeleteMapping("/{productId}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String productId) {
        return Mono.fromCallable(() -> productRepository.findById(productId))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(opt -> {
                    if (opt.isEmpty()) {
                        return Mono.just(ResponseEntity.<Void>notFound().build());
                    }
                    return Mono.fromRunnable(() -> productRepository.deleteById(productId))
                            .subscribeOn(Schedulers.boundedElastic())
                            .thenReturn(ResponseEntity.<Void>noContent().build());
                });
    }
}
