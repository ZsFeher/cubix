package hu.cubix.hr.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Employee;

@RestController
@RequestMapping("api/employees")
public class HRRestController {

	private Map<Long, EmployeeDto> employees = new HashMap<>();

	{
		employees.put(1L, new EmployeeDto(1, "Programmer", 10000, LocalDateTime.of(2009, 3,28, 10, 10)));
	}

	@GetMapping
	public List<EmployeeDto> listEmployees()
	{
		return new ArrayList<>(employees.values());
	}

	@GetMapping("/{id}")
	public EmployeeDto findEmployeeById(@PathVariable long id)
	{
		return employees.get(id);
	}

	@PostMapping
	public EmployeeDto createEmployee(@RequestBody EmployeeDto employee)
	{
		employees.put(employee.getId(), employee);
		return employee;
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto employee)
	{
		if(!employees.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}

		employees.put(id, employee);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id)
	{
		employees.remove(id);
	}

	@GetMapping("/listbysalary/{salary}")
	public ResponseEntity<Map<Long,EmployeeDto>> listEmployeeBySalary(@PathVariable int salary)
	{
			Map<Long,EmployeeDto> filteredEmployees = employees.entrySet().stream().filter(map -> map.getValue().getSalary() > salary).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));

			return ResponseEntity.ok(filteredEmployees);
	}

}
