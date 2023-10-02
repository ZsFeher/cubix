package hu.cubix.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.hr.model.Employee;

@Service
public class SalaryService {

	@Autowired
	private EmployeeService employeeService;

	public int getRaisedSalary(Employee employee)
	{
		int payRaise = employeeService.getPayRaisePercent(employee);
		double raisedSalary = (int)employee.getSalary()*((1+payRaise/100.0));
		employee.setSalary((int)raisedSalary);

		return employee.getSalary();
	}

}
