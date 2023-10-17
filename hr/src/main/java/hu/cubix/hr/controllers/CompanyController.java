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

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.mapper.CompanyMapper;
import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController{

	@Autowired
	CompanyService companyService;

	@Autowired
	CompanyMapper cMapper;

	@GetMapping
	public List<CompanyDto> listCompanies(@RequestParam(value="full", required = false) boolean full)
	{
		List<CompanyDto> companies = cMapper.companyListToDto(companyService.getAll());

		if(full){

			return companies;

		}

		return companies.stream().map(CompanyDto::copyWithoutEmployees).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CompanyDto findCompanyById(@PathVariable long id, @RequestParam(value="full", required = false) boolean full)
	{
		Company company = companyService.findById(id);

		if(full){
			return cMapper.companyToDto(company);
		}

		return cMapper.companyToDto(company).copyWithoutEmployees();
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto company)
	{
		Company c = cMapper.dtoToCompany(company);

		companyService.create(c);

		return cMapper.companyToDto(c);
	}

	@PutMapping("/{id}")
	public CompanyDto updateCompany(@PathVariable long id, @RequestBody CompanyDto company)
	{
		if(!companyService.getAll().contains(id))
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Company c = cMapper.dtoToCompany(company);

		Company uCompany = companyService.update(c);

		return cMapper.companyToDto(uCompany);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id)
	{
		companyService.delete(id);
	}

	@PutMapping("/addEmployee/{id}")
	public ResponseEntity<CompanyDto> addEmployee(@PathVariable int id, @RequestBody EmployeeDto employee)
	{
		if(!companyService.getAll().contains(id)){
			return ResponseEntity.notFound().build();
		}

		companyService.getAll().get(id).getEmployees().add(employee);

		return ResponseEntity.ok(cMapper.companyToDto(companyService.getAll().get(id)));
	}

	@DeleteMapping("/deleteEmployee/{companyId}/{employeeId}")
	public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable int companyId, @PathVariable int employeeId)
	{
		if(!companyService.getAll().contains(companyId)){
			return ResponseEntity.notFound().build();
		}

		Company comp = companyService.getAll().get(companyId);

		comp.getEmployees().removeIf(emp -> emp.getId() == employeeId);

		return ResponseEntity.ok(cMapper.companyToDto(comp));
	}

	/*@PutMapping("/replaceEmployeeList/{companyId}")
	public ResponseEntity<CompanyDto> replaceEmployeeList(@PathVariable long companyId, @RequestBody List<EmployeeDto> employees)
	{
		if(!companies.containsKey(companyId)){
			return ResponseEntity.notFound().build();
		}
		CompanyDto comp = companies.get(companyId);
		comp.setEmployees(employees);

		return ResponseEntity.ok(comp);
	}*/
}
