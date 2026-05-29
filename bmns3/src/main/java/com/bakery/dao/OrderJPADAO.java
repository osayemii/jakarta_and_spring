package com.bakery.dao;

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
			return em.createQuery("FROM Order", Order.class).getResultList();
		} finally {
			em.close();
		}
	}
}
