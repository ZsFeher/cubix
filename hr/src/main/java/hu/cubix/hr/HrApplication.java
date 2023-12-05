package hu.cubix.hr;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import hu.cubix.hr.model.NameAndId;
import hu.cubix.hr.model.TimeOffRequest;
import hu.cubix.hr.repositories.CompanyRepository;
import hu.cubix.hr.repositories.EmployeeRepository;
import hu.cubix.hr.service.InitDbService;
import hu.cubix.hr.service.SalaryService;
import hu.cubix.hr.service.TimeOffRequestService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	@Autowired
	InitDbService initDbService;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TimeOffRequestService timeOffRequestService;

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
/*
		Employee admin1 = employeeRepository.findByUsername("admin");
		List<NameAndId> managedEmployees = employeeRepository.getManagedEmployees(admin1.getId());

		System.out.println(managedEmployees.stream().map(NameAndId::getName).toArray(String[]::new));

		TimeOffRequest example = new TimeOffRequest();
		example.setStatusCode(1);

		LocalDate toStart = LocalDate.of(2023,12,1);
		LocalDate toEnd = LocalDate.of(2023,12,31);

		example.setStartDate(toStart);
		example.setEndDate(toEnd);

		LocalDateTime entry = LocalDateTime.of(2013, 10,28,14,33,48);

		Employee jan = new Employee(23, "Jan", 100, entry);
		example.setApprover(jan);

		Employee vic = new Employee(25, "Victor", 100, entry);
		example.setRelatedEmployee(vic);

		LocalDateTime from = LocalDateTime.of(2023, 10,28,14,33,48);
		LocalDateTime to = LocalDateTime.of(2023, 12,28,14,33,48);


		List<TimeOffRequest> timeOffRequests = timeOffRequestService.searchTO(example, from, to);
		System.out.println(timeOffRequests);
*/
	}
}
