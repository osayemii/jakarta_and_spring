package com.bakery.dao;

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
			return em.createQuery("FROM Product", Product.class).getResultList();
		} finally {
			em.close();
		}
	}
}
