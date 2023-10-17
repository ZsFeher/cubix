package hu.cubix.hr.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.mapper.EmployeeMapper;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.service.EmployeeService;
import hu.cubix.hr.service.ProfileEmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/employees")
public class HRRestController {

	@Autowired
	private ProfileEmployeeService employeeService;

	@Autowired
	private EmployeeMapper eMapper;

	@GetMapping
	public List<EmployeeDto> listEmployees()
	{
		List<Employee> employees = employeeService.getAll();
		return eMapper.employeeListToDto(employees);
	}

	@GetMapping("/{id}")
	public EmployeeDto findEmployeeById(@PathVariable long id)
	{
		Employee employee = employeeService.findById(id);
		return eMapper.employeeToDto(employee);
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employee)
	{
		Employee e = eMapper.dtoToEmployee(employee);

		employeeService.create(e);
		return eMapper.employeeToDto(e);
	}

	@PutMapping("/{id}")
	public EmployeeDto updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employee)
	{
		if(!employeeService.getAll().contains(id))
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Employee e = eMapper.dtoToEmployee(employee);

		Employee uEmployee = employeeService.update(e);
		return eMapper.employeeToDto(uEmployee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		employeeService.delete(id);
	}

	@GetMapping("/listbysalary/{salary}")
	public List<EmployeeDto> listEmployeeBySalary(@PathVariable int salary)
	{
			List<Employee> filteredEmployees = employeeService.getAll().stream().filter(emp -> emp.getSalary() > salary).toList();

			return eMapper.employeeListToDto(filteredEmployees);
	}

}
