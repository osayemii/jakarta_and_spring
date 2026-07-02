package com.bakery.dao;

import java.time.LocalDate;
import java.util.List;

import com.bakery.model.Order;

import jakarta.persistence.*;

public class OrderJPADAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bakeryPU");

	public void save(Order order) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(order);
		em.getTransaction().commit();
		em.close();
	}

	public List<Order> getAll() {
		EntityManager em = emf.createEntityManager();
		try {
			// Note: entity name "Order" is aliased below because ORDER is a JPQL/HQL
			// reserved keyword, so a bare "FROM Order" (no alias) is fragile.
			return em.createQuery("SELECT o FROM Order o ORDER BY o.id", Order.class).getResultList();
		} finally {
			em.close();
		}
	}

	public Order findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Order.class, id);
		} finally {
			em.close();
		}
	}

	public void update(int id, String customerName, String productName, String orderedGoods, double totalPrice,
			LocalDate orderedDate) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Order order = em.find(Order.class, id);
			if (order == null) {
				em.getTransaction().rollback();
				throw new IllegalArgumentException("Order not found: id " + id);
			}
			order.setCustomerName(customerName);
			order.setProductName(productName);
			order.setOrderedGoods(orderedGoods);
			order.setTotalPrice(totalPrice);
			order.setOrderedDate(orderedDate);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Order order = em.find(Order.class, id);
			if (order != null) {
				em.remove(order);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public long count() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT COUNT(o) FROM Order o", Long.class).getSingleResult();
		} finally {
			em.close();
		}
	}

	public double totalRevenue() {
		EntityManager em = emf.createEntityManager();
		try {
			Double sum = em.createQuery("SELECT COALESCE(SUM(o.totalPrice), 0.0) FROM Order o", Double.class)
					.getSingleResult();
			return sum == null ? 0.0 : sum;
		} finally {
			em.close();
		}
	}
}
