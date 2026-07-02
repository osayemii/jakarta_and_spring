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

	public int getId() {
		return id;
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

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}
}
