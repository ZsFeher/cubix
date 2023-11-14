package hu.cubix.hr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import hu.cubix.hr.model.Employee;
import hu.cubix.hr.repositories.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public abstract class EmployeeMainService implements EmployeeService {

	/*
	@PersistenceContext
	EntityManager em;

	private Map<Long, Employee> employees = new HashMap<>();
	{

		employees.put(1L,new Employee(1,"Nicholas","programmer",10000, LocalDateTime.of(2009, 3,28,14,33,48)));

	}
	*/

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAll()
	{
		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(long id)
	{
		return employeeRepository.findById(id);
	}

	@Transactional
	public void delete(long id)
	{
		employeeRepository.deleteById(id);
	}

	@Transactional
	public Employee save(Employee employee)
	{
		return employeeRepository.save(employee);
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

	public Employee update(Employee employee)
	{
		if(employeeRepository.existsById(employee.getId())){
			return null;
		} else{
			return employeeRepository.save(employee);
		}
	}


}
