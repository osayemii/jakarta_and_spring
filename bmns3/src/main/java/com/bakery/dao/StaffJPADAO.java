package com.bakery.dao;

import java.util.List;

import com.bakery.model.Staff;

import jakarta.persistence.*;

public class StaffJPADAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bakeryPU");

	public void save(Staff staff) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(staff);
		em.getTransaction().commit();
		em.close();
	}

	public List<Staff> getAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("FROM Staff", Staff.class).getResultList();
		} finally {
			em.close();
		}
	}
}
