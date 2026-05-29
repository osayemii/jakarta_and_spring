package com.bakery.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "bakery_product")
@Access(AccessType.FIELD)
public class Product {

	@Id
	@GeneratedValue
	private int id;
	private String productName;
	private int productQuantity;
	private double productPrice;
	private LocalDate productionDate;

	protected Product() {}

	public Product(String productName, int productQuantity, double productPrice, LocalDate productionDate) {
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.productionDate = productionDate;
	}

	public String getProductName() {
		return productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public LocalDate getProductionDate() {
		return productionDate;
	}
}
