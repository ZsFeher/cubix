package hu.cubix.hr.controllers;

import static java.util.Collection.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController{

	private Map<Long, CompanyDto> companies = new HashMap<>();

	{
		EmployeeDto karl = new EmployeeDto(1, "Karl", "programmer", 10000, LocalDateTime.of(2011, 3, 11, 8,0));
		EmployeeDto suzan = new EmployeeDto(2, "Suzan", "chef", 7500, LocalDateTime.of(2022, 10, 2, 8,0));
		EmployeeDto leo = new EmployeeDto(3, "Leo", "pilot", 2000, LocalDateTime.of(2016, 2, 23, 8,0));
		EmployeeDto hannah = new EmployeeDto(4, "Hannah", "doctor", 21000, LocalDateTime.of(2023, 10, 10, 8,0));

		List<EmployeeDto> employees = new ArrayList<>(List.of(karl,suzan,leo,hannah));
		companies.put(1L, new CompanyDto(1, 110022 ,"SCC", "Békéscsaba", employees));
	}

	@GetMapping
	public List<CompanyDto> listCompanies(@RequestParam(value="full", required = false) boolean full)
	{
		if(full){
			return new ArrayList<>(companies.values());
		}

		return companies.values().stream().map(CompanyDto::copyWithoutEmployees).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CompanyDto findCompanyById(@PathVariable long id, @RequestParam(value="full", required = false) boolean full)
	{
		if(full){
			return companies.get(id);
		}

		return companies.get(id).copyWithoutEmployees();
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto company)
	{
		companies.put(company.getId(), company);
		return company;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> updateCompany(@PathVariable long id, @RequestBody CompanyDto company)
	{
		if(!companies.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}

		companies.put(id, company);
		return ResponseEntity.ok(company);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id)
	{
		companies.remove(id);
	}

	@PutMapping("/addEmployee/{id}")
	public ResponseEntity<CompanyDto> addEmployee(@PathVariable long id, @RequestBody EmployeeDto employee)
	{
		if(!companies.containsKey(id)){
			return ResponseEntity.notFound().build();
		}
		companies.get(id).getEmployees().add(employee);

		return ResponseEntity.ok(companies.get(id));
	}

	@DeleteMapping("/deleteEmployee/{companyId}/{employeeId}")
	public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long companyId, @PathVariable long employeeId)
	{
		if(!companies.containsKey(companyId)){
			return ResponseEntity.notFound().build();
		}
		CompanyDto comp = companies.get(companyId);

		comp.getEmployees().removeIf(emp -> emp.getId() == employeeId);

		return ResponseEntity.ok(comp);
	}

	@PutMapping("/replaceEmployeeList/{companyId}")
	public ResponseEntity<CompanyDto> replaceEmployeeList(@PathVariable long companyId, @RequestBody List<EmployeeDto> employees)
	{
		if(!companies.containsKey(companyId)){
			return ResponseEntity.notFound().build();
		}
		CompanyDto comp = companies.get(companyId);
		comp.setEmployees(employees);

		return ResponseEntity.ok(comp);
	}
}
