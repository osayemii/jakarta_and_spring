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
			return em.createQuery("SELECT s FROM Staff s ORDER BY s.id", Staff.class).getResultList();
		} finally {
			em.close();
		}
	}

	public Staff findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Staff.class, id);
		} finally {
			em.close();
		}
	}

	public void update(int id, String name, String email, int age, String phone, String address) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Staff staff = em.find(Staff.class, id);
			if (staff == null) {
				em.getTransaction().rollback();
				throw new IllegalArgumentException("Staff member not found: id " + id);
			}
			staff.setName(name);
			staff.setEmail(email);
			staff.setAge(age);
			staff.setPhone(phone);
			staff.setAddress(address);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public void delete(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Staff staff = em.find(Staff.class, id);
			if (staff != null) {
				em.remove(staff);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	public long count() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT COUNT(s) FROM Staff s", Long.class).getSingleResult();
		} finally {
			em.close();
		}
	}
}
