package hu.cubix.hr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;

@Service
public class CompanyService {


	private Map<Long, Company> companies = new HashMap<>();

	{
		EmployeeDto karl = new EmployeeDto(1, "Karl", "programmer", 10000, LocalDateTime.of(2011, 3, 11, 8,0));
		EmployeeDto suzan = new EmployeeDto(2, "Suzan", "chef", 7500, LocalDateTime.of(2022, 10, 2, 8,0));
		EmployeeDto leo = new EmployeeDto(3, "Leo", "pilot", 2000, LocalDateTime.of(2016, 2, 23, 8,0));
		EmployeeDto hannah = new EmployeeDto(4, "Hannah", "doctor", 21000, LocalDateTime.of(2023, 10, 10, 8,0));

		List<EmployeeDto> employees = new ArrayList<>(List.of(karl,suzan,leo,hannah));
		companies.put(1L, new Company(1, 110022 ,"SCC", "Békéscsaba", employees));
	}

	public List<Company> getAll()
	{
		return new ArrayList<>(companies.values());
	}

	public Company findById(long id)
	{
		return companies.get(id);
	}

	public void delete(long id)
	{
		companies.remove(id);
	}

	public Company save(Company company)
	{
		companies.put(company.getId(), company);
		return company;
	}

	public Company create(Company company)
	{
		if(findById(company.getId()) != null){
			return null;
		} else {
			return save(company);
		}
	}

	public Company update(Company company)
	{
		if(findById(company.getId()) == null){
			return null;
		} else{
			return save(company);
		}
	}
}
