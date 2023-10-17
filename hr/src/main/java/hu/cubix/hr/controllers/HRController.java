package hu.cubix.hr.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.cubix.hr.model.Employee;

@Controller
public class HRController {

	private List<Employee> employees = new ArrayList<>();

	{

	employees.add(new Employee(1,"Nicholas","programmer",10000,LocalDateTime.of(2009, 3,28,14,33,48)));

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

	@GetMapping("/modify")
	public String modifyEmployee(@RequestParam(value="employeeId", required = false) Integer employeeId, Model model)
	{
		Employee selectedEmployee = employees.stream().filter(emp -> employeeId == emp.getId()).findAny().orElse(null);

		model.addAttribute("employee",selectedEmployee);

		return "modifyEmployee";
	}

	@PostMapping("/updateEmployee")
	public String modifyEmployee(@ModelAttribute Employee employee, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()){
			return "index";
		}
		System.out.println(employee.getName());
		for(Employee emp : employees){
			if(emp.getId() == (employee.getId())){
				int index = employees.indexOf(emp);
				employees.set(index, employee);
			}
		}

		return "redirect:/";
	}

	@GetMapping("/delete/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId)
	{
		for(Employee emp : employees){
			if(emp.getId() == employeeId){
				int index = employees.indexOf(emp);
				employees.remove(index);
				break;
			}
		}

		return "redirect:/";
	}
}
