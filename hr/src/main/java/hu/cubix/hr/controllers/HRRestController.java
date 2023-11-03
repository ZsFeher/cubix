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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.mapper.EmployeeMapper;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.repositories.EmployeeRepository;
import hu.cubix.hr.service.EmployeeMainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/employees")
public class HRRestController {

	@Autowired
	private EmployeeMainService employeeService;

	@Autowired
	private EmployeeMapper eMapper;

	@Autowired
	private EmployeeRepository eRep;

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
	public EmployeeDto updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employee)
	{
		Employee emp = employeeService.findById(id);

		if(emp == null)
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

	@GetMapping("/findByJob/{job}")
	public List<EmployeeDto> findEmployeesByJob(@PathVariable String job)
	{
		List<Employee> employeeList = eRep.findByJob(job);
		System.out.println(employeeList);

		return eMapper.employeeListToDto(employeeList);
	}

	@GetMapping("/findByName/{namePart}")
	public List<EmployeeDto> findEmployeesByNamePrefix(@PathVariable String namePart)
	{
		List<Employee> employeeList = eRep.findByNameStartsWithIgnoreCase(namePart);

		return eMapper.employeeListToDto(employeeList);
	}

	@GetMapping("/findBetween")
	public List<EmployeeDto> findAllByEntryDateBetween(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to)
	{
		List<Employee> employeeList = eRep.findAllByEntryDateBetween(from, to);

		return eMapper.employeeListToDto(employeeList);
	}
}
