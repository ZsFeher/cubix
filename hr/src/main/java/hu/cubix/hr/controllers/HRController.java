package hu.cubix.hr.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.cubix.hr.model.Employee;

@Controller
public class HRController {

	private List<Employee> employees = new ArrayList<>();

	{

	employees.add(new Employee(1,"programmer",10000,LocalDateTime.of(2009, 3,28,14,33,48)));

	}

	@GetMapping("/")
	public String home(Map<String, Object> model)
	{
			model.put("employees", employees);
			model.put("newEmployee", new Employee());
			return "index";
	}

	@PostMapping("/employee")
	public String createEmployee(Employee employee)
	{
		employees.add(employee);
		return "redirect:/";
	}



}
