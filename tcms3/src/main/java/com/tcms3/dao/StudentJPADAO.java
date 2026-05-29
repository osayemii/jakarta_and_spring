package com.tcms3.dao;

import com.tcms3.model.Student;
import jakarta.persistence.*;

import java.util.List;

public class StudentJPADAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tcms3PU");
	
	public void save(Student student) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public List<Student> getAll() {
		
		EntityManager em = emf.createEntityManager();
		
		List<Student> list = em.createQuery("FROM Student", Student.class).getResultList();
		
		em.close();
		return list;
	}
}
