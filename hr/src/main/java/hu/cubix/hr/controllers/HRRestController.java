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
import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.Position;
import hu.cubix.hr.repositories.CompanyRepository;
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
	@Autowired
	private CompanyRepository companyRepository;

	@GetMapping
	public List<EmployeeDto> listEmployees()
	{
		List<Employee> employees = employeeService.getAll();
		return eMapper.employeeListToDto(employees);
	}

	@GetMapping("/{id}")
	public EmployeeDto findEmployeeById(@PathVariable long id)
	{
		Employee employee = findByIdOrThrow(id);
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
		Employee emp = findByIdOrThrow(id);

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
			List<Employee> filteredEmployees = eRep.findBySalaryGreaterThan(salary);

			return eMapper.employeeListToDto(filteredEmployees);
	}

	@GetMapping("/findByJob/{job}")
	public List<EmployeeDto> findEmployeesByJob(@PathVariable String job)
	{
		List<Employee> employeeList = eRep.findByPositionName(job);

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

	@GetMapping("/search")
	public List<EmployeeDto> searchForEmployees()
	{
		Company comp = new Company(111,"R","Bcs",null);
		Position pos = new Position("dog trainer", null, 200);
		Employee example = new Employee("Joh" ,9988, LocalDateTime.of(2009, 3, 29,14, 33, 48));
		example.setPosition(pos);
		example.setCompany(comp);

		List<Employee> employeeList = employeeService.searchEmployee(example);
		return eMapper.employeeListToDto(employeeList);
	}

	private Employee findByIdOrThrow(long id) {
		return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
