package com.bank.servlet;

import java.io.IOException;
import java.util.List;

import com.bank.model.Bank;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * CRUD for {@link Bank} / {@code Customer_Details}; forwards to {@code Bank_Home_Page.jsp}.
 */
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final class EmfHolder {
		private static final EntityManagerFactory INSTANCE =
				Persistence.createEntityManagerFactory("bankManagementPU");

		private EmfHolder() {
		}
	}

	private static boolean isLoggedIn(HttpServletRequest req) {
		HttpSession s = req.getSession(false);
		return s != null && Boolean.TRUE.equals(s.getAttribute("bankLoggedIn"));
	}

	private void loadCustomerList(EntityManager em, HttpServletRequest req) {
		TypedQuery<Bank> q = em.createQuery("SELECT b FROM Bank b ORDER BY b.name", Bank.class);
		List<Bank> customers = q.getResultList();
		req.setAttribute("customers", customers);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (!isLoggedIn(req)) {
			res.sendRedirect(req.getContextPath() + "/UserLogin.jsp");
			return;
		}
		EntityManager em = EmfHolder.INSTANCE.createEntityManager();
		try {
			loadCustomerList(em, req);
			req.getRequestDispatcher("/Bank_Home_Page.jsp").forward(req, res);
		} finally {
			em.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (!isLoggedIn(req)) {
			res.sendRedirect(req.getContextPath() + "/UserLogin.jsp");
			return;
		}

		EntityManager em = EmfHolder.INSTANCE.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			if (!tx.isActive()) {
				tx.begin();
			}

			String action = req.getParameter("action");
			if (action == null) {
				action = "list";
			}

			if ("view".equals(action)) {
				String id = trim(req.getParameter("customerId"));
				if (id != null) {
					Bank c = em.find(Bank.class, id);
					req.setAttribute("customer", c);
					if (c == null) {
						req.setAttribute("errorMessage", "Customer not found.");
					}
				} else {
					req.setAttribute("errorMessage", "Please select a customer from the list.");
				}
			} else if ("add".equals(action)) {
				String id = trim(req.getParameter("customerId"));
				String name = trim(req.getParameter("name"));
				String ageStr = trim(req.getParameter("age"));
				String dob = trim(req.getParameter("dateOfBirth"));
				if (id == null || name == null) {
					req.setAttribute("errorMessage", "Customer ID and Name are required.");
				} else if (em.find(Bank.class, id) != null) {
					req.setAttribute("errorMessage", "A customer with this ID already exists.");
				} else {
					Bank b = new Bank();
					b.setCustomerId(id);
					b.setName(name);
					b.setAge(parseAge(ageStr));
					b.setDateOfBirth(dob);
					em.persist(b);
					req.setAttribute("infoMessage", "Customer added successfully.");
				}
			} else if ("update".equals(action)) {
				String id = trim(req.getParameter("customerId"));
				String name = trim(req.getParameter("name"));
				String ageStr = trim(req.getParameter("age"));
				String dob = trim(req.getParameter("dateOfBirth"));
				if (id == null) {
					req.setAttribute("errorMessage", "Customer ID is required for update.");
				} else {
					Bank b = em.find(Bank.class, id);
					if (b == null) {
						req.setAttribute("errorMessage", "Customer not found.");
					} else {
						if (name != null) {
							b.setName(name);
						}
						b.setAge(parseAge(ageStr));
						b.setDateOfBirth(dob);
						em.merge(b);
						req.setAttribute("infoMessage", "Customer updated successfully.");
						req.setAttribute("customer", b);
					}
				}
			} else if ("delete".equals(action)) {
				String id = trim(req.getParameter("customerId"));
				if (id == null) {
					req.setAttribute("errorMessage", "Please select a customer to delete.");
				} else {
					Bank b = em.find(Bank.class, id);
					if (b != null) {
						em.remove(b);
						req.setAttribute("infoMessage", "Customer deleted.");
					} else {
						req.setAttribute("errorMessage", "Customer not found.");
					}
				}
			} else if ("edit".equals(action)) {
				String id = trim(req.getParameter("customerId"));
				if (id != null) {
					Bank c = em.find(Bank.class, id);
					req.setAttribute("customer", c);
					req.setAttribute("editable", Boolean.TRUE);
					if (c == null) {
						req.setAttribute("errorMessage", "Customer not found.");
					}
				} else {
					req.setAttribute("errorMessage", "Please select a customer from the list.");
				}
			}

			loadCustomerList(em, req);
			tx.commit();
			req.getRequestDispatcher("/Bank_Home_Page.jsp").forward(req, res);
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception ignored) {
			}
			ex.printStackTrace();
			res.sendError(500, "Error: " + ex.getMessage());
		} finally {
			em.close();
		}
	}

	private static String trim(String s) {
		if (s == null) {
			return null;
		}
		String t = s.trim();
		return t.isEmpty() ? null : t;
	}

	private static Integer parseAge(String ageStr) {
		if (ageStr == null) {
			return null;
		}
		try {
			return Integer.valueOf(ageStr.trim());
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
