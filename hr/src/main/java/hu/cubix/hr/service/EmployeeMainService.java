package hu.cubix.hr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.cubix.hr.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public abstract class EmployeeMainService implements EmployeeService {

	@PersistenceContext
	EntityManager em;
/*
	private Map<Long, Employee> employees = new HashMap<>();
	{

		employees.put(1L,new Employee(1,"Nicholas","programmer",10000, LocalDateTime.of(2009, 3,28,14,33,48)));

	}
*/

	public List<Employee> getAll()
	{
		return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}

	public Employee findById(long id)
	{
		return em.find(Employee.class, id);
	}

	@Transactional
	public void delete(long id)
	{
		em.remove(findById(id));
	}

	@Transactional
	public Employee save(Employee employee)
	{
		if(employee.getId() == 0){
			em.persist(employee);
		} else {
			employee = em.merge(employee);
		}

		return employee;
	}

	@Transactional
	public Employee create(Employee employee)
	{
		if(findById(employee.getId()) != null){
			return null;
		} else {
			return save(employee);
		}
	}

	@Transactional
	public Employee update(Employee employee)
	{
		if(findById(employee.getId()) == null){
			return null;
		} else{
			return save(employee);
		}
	}


}
