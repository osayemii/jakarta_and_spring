package com.tcms3.dao;

import java.util.*;

import com.tcms3.model.Course;

import jakarta.persistence.*;

public class CourseJPADAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tcms3PU");
	
	public void save(Course course) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(course);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<Course> getAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("FROM Course", Course.class).getResultList();
		} finally {
			em.close();
		}
	}
}