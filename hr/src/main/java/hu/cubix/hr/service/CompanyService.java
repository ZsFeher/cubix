package hu.cubix.hr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.repositories.CompanyRepository;
import hu.cubix.hr.repositories.EmployeeRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	/*
		private Map<Long, Company> companies = new HashMap<>();

		{
			Employee karl = new Employee(1, "Karl", "programmer", 10000, LocalDateTime.of(2011, 3, 11, 8,0));
			Employee suzan = new Employee(2, "Suzan", "chef", 7500, LocalDateTime.of(2022, 10, 2, 8,0));
			Employee leo = new Employee(3, "Leo", "pilot", 2000, LocalDateTime.of(2016, 2, 23, 8,0));
			Employee hannah = new Employee(4, "Hannah", "doctor", 21000, LocalDateTime.of(2023, 10, 10, 8,0));

			List<Employee> employees = new ArrayList<>(List.of(karl,suzan,leo,hannah));
			companies.put(1L, new Company(1, 110022 ,"SCC", "Békéscsaba", employees));
		}
	*/
	public List<Company> getAll()
	{
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long id)
	{
		return companyRepository.findById(id);
	}

	public void delete(long id)
	{
		companyRepository.deleteById(id);
	}

	public Company save(Company company)
	{
		return companyRepository.save(company);
	}

	public Company create(Company company)
	{
		if(findById(company.getId()) != null){
			return null;
		} else {
			return companyRepository.save(company);
		}
	}

	public Company update(Company company)
	{
		if(!companyRepository.existsById(company.getId())) {
			return null;
		}
		return companyRepository.save(company);
	}

	/*házi megoldás
	public Company addEmployee(long id, Employee employee)
	{
		Company company = companyRepository.findById(id).get();
		company.addEmployee(employee);
		employeeRepository.save(employee);
		return company;
	}

	public Company deleteEmployee(long id, long employeeId)
	{
		Company company = companyRepository.findById(id).get();
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeReposítory.save(e);
		return company;
	}

	public Company replaceEmployees(long id, List<Employee> employees)
	{
		Company company = companyRepository.findById(id).get();
		company.getEmployees().forEach(e -> e.setCompany(null));
		company.getEmployees().clear();
		employees.forEach(e -> {
			company.addEmployee(employeeRepository.save(e));
		});
		return company;
	}
	*/
}
