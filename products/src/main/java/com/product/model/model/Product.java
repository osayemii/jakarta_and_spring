package com.product.model.model;

import com.product.model.validation.ProductId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Product {

    @ProductId
    private String productId;

    @NotBlank(message = "Product Name is required")
    private String productName;

    @NotBlank(message = "Product Description is required")
    private String productDescription;

    @NotNull(message = "Unit Price is required")
    @Positive(message = "Unit Price must be positive")
    private Double unitPrice;

    public Product() {
    }

    public Product(String productId, String productName, String productDescription, Double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
                + productDescription + ", unitPrice=" + unitPrice + "]";
    }
}
