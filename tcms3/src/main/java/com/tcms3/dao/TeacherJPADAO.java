package com.tcms3.dao;

import java.util.List;

import com.tcms3.model.Teacher;

import jakarta.persistence.*;

public class TeacherJPADAO {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tcms3PU");
		
		public void save(Teacher teacher) {
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			em.persist(teacher);
			em.getTransaction().commit();
			
			em.close();
		}
		
		public List<Teacher> getAll() {
			
			EntityManager em = emf.createEntityManager();
			
			List<Teacher> list = em.createQuery("FROM Teacher", Teacher.class).getResultList();
			
			em.close();
			return list;
		}
}