package com.rms.model;

import java.time.LocalDate;

public class DiningOrder {

	private final int tableNumber;
	private final String dishName;
	private final String notes;
	private final double totalPrice;
	private final LocalDate orderDate;

	public DiningOrder(int tableNumber, String dishName, String notes, double totalPrice, LocalDate orderDate) {
		this.tableNumber = tableNumber;
		this.dishName = dishName;
		this.notes = notes;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public String getDishName() {
		return dishName;
	}

	public String getNotes() {
		return notes;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}
}
