package hu.cubix.hr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import hu.cubix.hr.model.Employee;

public abstract class ProfileEmployeeService implements EmployeeService {

	private Map<Long, Employee> employees = new HashMap<>();

	public List<Employee> getAll()
	{
		return new ArrayList<>(employees.values());
	}

	public Employee findById(long id)
	{
		return employees.get(id);
	}

	public void delete(long id)
	{
		employees.remove(id);
	}

	public Employee save(Employee employee)
	{
		employees.put(employee.getId(), employee);
		return employee;
	}

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
		if(findById(employee.getId()) == null){
			return null;
		} else{
			return save(employee);
		}
	}
}
