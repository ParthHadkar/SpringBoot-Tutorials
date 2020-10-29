package com.springboot.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.Employee;

@Repository
public class EmployeeDaoJPAImpl implements EmployeeDao {

	// define variable for EntityManager
	private EntityManager entityManager;

	// inject dependency using constructor injection
	@Autowired
	public EmployeeDaoJPAImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = null;
		try {
			Query query = entityManager.createQuery("FROM Employee e");
			employees = query.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee employee = null;
		try {
			employee = entityManager.find(Employee.class, id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public String saveEmployee(Employee employee) {
		String response = null;
		try {
			int eId = employee.getId();
			//System.out.println(eId+" : "+eId);
			Employee dbEmployee = entityManager.merge(employee);
			// update with id from db .... so that we can get generated id for save/insert
			employee.setId(dbEmployee.getId());
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
			
			//Employee employee = entityManager.find(Employee.class, id);
			//entityManager.remove(employee);
			Query query = entityManager.createQuery("DELETE FROM Employee e WHERE e.id=:eid");
			query.setParameter("eid", id);
			query.executeUpdate();
			response = "Employee deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public boolean checkEmailId(String emailId, int eid) {
		boolean valid =false;
		try {
			Query query = entityManager.createQuery(
					(eid!=0)?"FROM Employee e WHERE e.emailId=:eemailId AND e.id!=:eid":
						"FROM Employee e WHERE e.emailId=:eemailId",Employee.class);
			query.setParameter("eemailId", emailId);
			if(eid != 0) {
			query.setParameter("eid", eid);
			}
			Employee employee = (Employee) query.getSingleResult();
			valid = (employee!=null)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}

}
