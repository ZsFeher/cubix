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

			Employee john = new Employee(1, "John", "programmer", 10000, LocalDateTime.of(2009, 3, 28,14, 33, 48));
			Employee ariel = new Employee(2, "Ariel", "economist", 7000, LocalDateTime.of(2020, 1, 12,14, 33, 48));
			Employee aron = new Employee(3, "Mike" , "cashier", 2300, LocalDateTime.of(2015, 1, 12,14, 33, 48));

			System.out.println(salaryService.getRaisedSalary(john));
			System.out.println(salaryService.getRaisedSalary(ariel));
			System.out.println(salaryService.getRaisedSalary(aron));
	}
}
