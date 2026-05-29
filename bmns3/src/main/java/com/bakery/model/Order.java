package com.bakery.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "bakery_order")
@Access(AccessType.FIELD)
public class Order {

	@Id
	@GeneratedValue
	private int id;
	private String customerName;
	private String productName;
	private String orderedGoods;
	private double totalPrice;
	private LocalDate orderedDate;

	protected Order() {}

	public Order(String customerName, String productName, String orderedGoods, double totalPrice, LocalDate orderedDate) {
		this.customerName = customerName;
		this.productName = productName;
		this.orderedGoods = orderedGoods;
		this.totalPrice = totalPrice;
		this.orderedDate = orderedDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getProductName() {
		return productName;
	}

	public String getOrderedGoods() {
		return orderedGoods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public LocalDate getOrderedDate() {
		return orderedDate;
	}
}
