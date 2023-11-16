package hu.cubix.hr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.Position;
import hu.cubix.hr.model.Qualification;
import hu.cubix.hr.repositories.CompanyRepository;
import hu.cubix.hr.repositories.EmployeeRepository;
import hu.cubix.hr.repositories.PositionRepository;

@Service
public class InitDbService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PositionRepository positionRepository;

	public void clearDb()
	{
		employeeRepository.deleteAll();
		companyRepository.deleteAll();
	}

	public void insertTestData()
	{
		Position pos1 = new Position("dog trainer", Qualification.HIGH_SCHOOL, 10000 );
		Position pos2 = new Position("programmer", Qualification.HIGH_SCHOOL, 20000 );
		Position pos3 = new Position("chef", Qualification.HIGH_SCHOOL, 7000 );

		positionRepository.save(pos1);
		positionRepository.save(pos2);
		positionRepository.save(pos3);

		Employee john = new Employee(1,"John",10000, LocalDateTime.of(2009, 3,28,14,33,48));
		Employee chuck = new Employee(2,"Chuck",7000, LocalDateTime.of(2019, 10,18,14,33,48));
		Employee kim = new Employee(3,"Kim",15000, LocalDateTime.of(2021, 2,2,14,33,48));
		Employee alan = new Employee(4,"Alan",2000, LocalDateTime.of(2010, 1,10,14,33,48));
		Employee hannah = new Employee(5,"Hannah",20000, LocalDateTime.of(2023, 9,12,14,33,48));
		Employee joe = new Employee(6,"Joe",40000, LocalDateTime.of(2001, 10,12,14,33,48));



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
		john.setPosition(pos1);
		employeeRepository.save(john);
		chuck.setCompany(rr);
		chuck.setPosition(pos2);
		employeeRepository.save(chuck);

		List<Employee> employees2 = new ArrayList<>();

		employees2.add(kim);
		employees2.add(alan);
		employees2.add(hannah);
		employees2.add(joe);

		Company zszs = new Company(2,456,"ZS&ZS","Bcs.", employees2);
		companyRepository.save(zszs);

		kim.setCompany(zszs);
		kim.setPosition(pos1);
		employeeRepository.save(kim);
		alan.setCompany(zszs);
		alan.setPosition(pos3);
		employeeRepository.save(alan);
		hannah.setCompany(zszs);
		hannah.setPosition(pos2);
		employeeRepository.save(hannah);
		joe.setCompany(zszs);
		joe.setPosition(pos2);
		employeeRepository.save(joe);

	}

}
