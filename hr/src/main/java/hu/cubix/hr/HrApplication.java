package hu.cubix.hr;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubix.hr.model.Employee;
import hu.cubix.hr.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

			Employee john = new Employee(1, "programmer", 10000, LocalDateTime.of(2009, 03, 28,14, 33, 48, 640000));
			Employee ariel = new Employee(2, "economist", 7000, LocalDateTime.of(2020, 01, 12,14, 33, 48, 640000));
			Employee aron = new Employee(3, "cashier", 2300, LocalDateTime.of(2015, 01, 12,14, 33, 48, 640000));

			System.out.println(salaryService.getRaisedSalary(john));
			System.out.println(salaryService.getRaisedSalary(ariel));
			System.out.println(salaryService.getRaisedSalary(aron));
	}
}
