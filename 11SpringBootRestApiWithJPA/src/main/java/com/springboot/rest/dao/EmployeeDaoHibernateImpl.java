package com.springboot.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.Employee;

@Repository
public class EmployeeDaoHibernateImpl implements EmployeeDao {

	// define variable for EntityManager
	private EntityManager entityManager;
	
	// inject dependency using constructor injection
	@Autowired
	public EmployeeDaoHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = null;
		try {
			//get hibernate current session
			Session currentSession = entityManager.unwrap(Session.class);
			Query<Employee> query = currentSession.createQuery("FROM Employee e",Employee.class);
			employees = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee employee = null;
		try {
			//get hibernate current session
			Session currentSession = entityManager.unwrap(Session.class);
			employee = currentSession.get(Employee.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public String saveEmployee(Employee employee) {
		String response = null;
		try {
			//get hibernate current session
			int eId = employee.getId();
			//System.out.println(eId+" : "+eId);
			Session currentSession = entityManager.unwrap(Session.class);
			//System.out.println(employee+" : "+employee);
			if(currentSession!=null) {
				currentSession.clear();
			}
			currentSession.saveOrUpdate(employee);
			//System.out.println(employee+" : "+employee);
			response = (eId!=0)?"Employee updated successfully":"Employee saved successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String deleteEmployee(int id) {
		String response = null;
		try {
			//get hibernate current session
			Session currentSession = entityManager.unwrap(Session.class);
			
			//Employee employee = currentSession.get(Employee.class, id);
			//currentSession.delete(employee);
			Query query = currentSession.createQuery("DELETE FROM Employee e WHERE e.id=:eid");
			query.setParameter("eid", id);
			query.executeUpdate();
			response = "Employee deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public boolean checkEmailId(String emailId,int eid) {
		boolean valid =false;
		try {
			//get hibernate current session
			Session currentSession = entityManager.unwrap(Session.class);
			Query<Employee> query = currentSession.createQuery(
					(eid!=0)?"FROM Employee e WHERE e.emailId=:eemailId AND e.id!=:eid":
						"FROM Employee e WHERE e.emailId=:eemailId",Employee.class);
			query.setParameter("eemailId", emailId);
			if(eid != 0) {
			query.setParameter("eid", eid);
			}
			Employee employee = query.getSingleResult();
			valid = (employee!=null)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

}
