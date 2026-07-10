package com.example.productwebflux.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @NotBlank(message = "Product name is required")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be >= 0")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be >= 0")
    @Column(nullable = false)
    private Double price;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @NotBlank(message = "Manufacturer is required")
    @Column(nullable = false)
    private String manufacturer;

    @NotNull(message = "Expiry date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    // Default constructor
    public Product() {}

    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
}
