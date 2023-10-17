package hu.cubix.hr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.cubix.hr.model.Employee;
import hu.cubix.hr.service.EmployeeService;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping
	public int getPayRaise(@RequestBody Employee employee)
	{
		int payRaise = employeeService.getPayRaisePercent(employee);

		return payRaise;
	}

}
