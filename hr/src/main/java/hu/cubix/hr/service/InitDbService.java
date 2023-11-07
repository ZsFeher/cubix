package hu.cubix.hr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.repositories.CompanyRepository;
import hu.cubix.hr.repositories.EmployeeRepository;

@Service
public class InitDbService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public void clearDb()
	{
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
	}

	public void insertTestData()
	{
		Employee john = new Employee(1,"John","programmer",10000, LocalDateTime.of(2009, 3,28,14,33,48));
		Employee chuck = new Employee(2,"Chuck","driver",7000, LocalDateTime.of(2019, 10,18,14,33,48));
		Employee kim = new Employee(3,"Kim","programmer",15000, LocalDateTime.of(2021, 2,2,14,33,48));
		Employee alan = new Employee(4,"Alan","cashier",2000, LocalDateTime.of(2010, 1,10,14,33,48));
		Employee hannah = new Employee(5,"Hannah","doctor",20000, LocalDateTime.of(2023, 9,12,14,33,48));
		Employee joe = new Employee(6,"Joe","doctor",40000, LocalDateTime.of(2001, 10,12,14,33,48));

		employeeRepository.save(john);
		employeeRepository.save(chuck);
		employeeRepository.save(kim);
		employeeRepository.save(alan);
		employeeRepository.save(hannah);
		employeeRepository.save(joe);

		List<Employee> employees1 = new ArrayList<>();

		employees1.add(john);
		employees1.add(chuck);

		Company rr = new Company(1,123,"RR","Bp.", employees1);
		companyRepository.save(rr);

		john.setCompany(rr);
		employeeRepository.save(john);
		chuck.setCompany(rr);
		employeeRepository.save(chuck);

		List<Employee> employees2 = new ArrayList<>();

		employees2.add(kim);
		employees2.add(alan);
		employees2.add(hannah);
		employees2.add(joe);

		Company zszs = new Company(2,456,"ZS&ZS","Bcs.", employees2);
		companyRepository.save(zszs);

		kim.setCompany(zszs);
		employeeRepository.save(kim);
		alan.setCompany(zszs);
		employeeRepository.save(alan);
		hannah.setCompany(zszs);
		employeeRepository.save(hannah);
		joe.setCompany(zszs);
		employeeRepository.save(joe);

	}

}
