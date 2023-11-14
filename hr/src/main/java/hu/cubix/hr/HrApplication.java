package hu.cubix.hr;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.repositories.CompanyRepository;
import hu.cubix.hr.service.InitDbService;
import hu.cubix.hr.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	@Autowired
	InitDbService initDbService;

	@Autowired
	CompanyRepository companyRepository;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
			Employee john = new Employee(1, "John", "programmer", 10000, LocalDateTime.of(2009, 3, 28,14, 33, 48));
			Employee ariel = new Employee(2, "Ariel", "economist", 7000, LocalDateTime.of(2020, 1, 12,14, 33, 48));
			Employee aron = new Employee(3, "Mike" , "cashier", 2300, LocalDateTime.of(2015, 1, 12,14, 33, 48));

			System.out.println(salaryService.getRaisedSalary(john));
			System.out.println(salaryService.getRaisedSalary(ariel));
			System.out.println(salaryService.getRaisedSalary(aron));
		*/

		initDbService.clearDb();
		initDbService.insertTestData();

		Pageable firstPageWithOneElement = PageRequest.of(0, 1);

		Page<Company> pagedResult = companyRepository.findAll(firstPageWithOneElement);

		System.out.println(pagedResult.getContent());

	}
}
