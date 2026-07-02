package com.bakery.dao;

import java.time.LocalDate;
import java.util.List;

import com.bakery.model.Product;

import jakarta.persistence.*;

public class ProductJPADAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bakeryPU");

	public void save(Product product) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		em.close();
	}

	public List<Product> getAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT p FROM Product p ORDER BY p.id", Product.class).getResultList();
		} finally {
			em.close();
		}
	}

	public Product findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Product.class, id);
		} finally {
			em.close();
		}
	}

	public void update(int id, String name, int quantity, double price, LocalDate productionDate) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Product product = em.find(Product.class, id);
			if (product == null) {
				em.getTransaction().rollback();
				throw new IllegalArgumentException("Menu item not found: id " + id);
			}
			product.setProductName(name);
			product.setProductQuantity(quantity);
			product.setProductPrice(price);
			product.setProductionDate(productionDate);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Product product = em.find(Product.class, id);
			if (product != null) {
				em.remove(product);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public long count() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
		} finally {
			em.close();
		}
	}
}
