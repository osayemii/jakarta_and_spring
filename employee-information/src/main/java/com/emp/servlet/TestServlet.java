package com.emp.servlet;

import java.io.IOException;
import java.util.List;

import com.emp.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** Lazy so deploy does not load JPA types before {@code WEB-INF/lib} is ready. */
	private static final class EmfHolder {
		private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("employeeInformationPU");

		private EmfHolder() {}
	}

	public TestServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager entityManager = EmfHolder.emf.createEntityManager();
		try {
			EntityTransaction trans = entityManager.getTransaction();
			if (!trans.isActive()) {
				trans.begin();
			}
			// Get the "action" parameter from the request
			String action = request.getParameter("action");
			if (action == null) {
				action = "view";
			}
			if ("view".equals(action)) {
				String employeeName = request.getParameter("Employee_Name");
				if (employeeName != null && !employeeName.isEmpty()) {
					// Fetch the employee data from the database based on the selected employee name
					Employee employee = entityManager.find(Employee.class, employeeName);
					// Check if employee is found
					if (employee != null) {
						request.setAttribute("Employee", employee);
					} else {
						request.setAttribute("errorMessage", "Employee not found or incorrect details");
					}
				} else {
					request.setAttribute("errorMessage", "Employee name is required");
				}
			} else if ("update".equals(action)) {
				String employeeName = request.getParameter("employeeName");
				String doj = request.getParameter("doj");
				String department = request.getParameter("department");
				String dob = request.getParameter("dob");
				String salary = request.getParameter("salary");
				Employee employee = entityManager.find(Employee.class, employeeName);
				if (employee != null) {
					employee.setDOJ(doj);
					employee.setDepartment(department);
					employee.setDOB(dob);
					employee.setSalary(salary);
					entityManager.merge(employee);
				}
			} else if ("delete".equals(action)) {
				String employeeName = request.getParameter("employeeName");
				Employee employee = entityManager.find(Employee.class, employeeName);
				if (employee != null) {
					entityManager.remove(employee);
				}
			} else if ("add".equals(action)) {
				String employeeName = trimToNull(request.getParameter("employeeName"));
				String doj = request.getParameter("doj");
				String department = request.getParameter("department");
				String dob = request.getParameter("dob");
				String salary = request.getParameter("salary");
				if (employeeName == null) {
					request.setAttribute("errorMessage", "Employee name is required");
				} else {
					Employee existing = entityManager.find(Employee.class, employeeName);
					if (existing != null) {
						request.setAttribute("errorMessage", "An employee with that name already exists.");
					} else {
						Employee employee = new Employee();
						employee.setEmployeeName(employeeName);
						employee.setDOJ(doj);
						employee.setDepartment(department);
						employee.setDOB(dob);
						employee.setSalary(salary);
						entityManager.persist(employee);
					}
				}
			}
			// Fetch all employee names to display in the dropdown
			TypedQuery<String> query = entityManager.createQuery("SELECT e.employeeName FROM Employee e", String.class);
			List<String> employeeNames = query.getResultList();
			request.setAttribute("employeeNames", employeeNames);
			trans.commit();

			request.getRequestDispatcher("home.jsp").forward(request, response);
		} catch (Exception ex) {
			try {
				EntityTransaction rollbackTx = entityManager.getTransaction();
				if (rollbackTx != null && rollbackTx.isActive()) {
					rollbackTx.rollback();
				}
			} catch (Exception ignored) {
				// ignore rollback failures
			}
			ex.printStackTrace(); // for debugging
			response.sendError(500, "Error fetching data: " + ex.getMessage());
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private static String trimToNull(String value) {
		if (value == null) {
			return null;
		}
		String t = value.trim();
		return t.isEmpty() ? null : t;
	}
}
