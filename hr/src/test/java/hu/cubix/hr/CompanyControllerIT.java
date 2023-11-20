package hu.cubix.hr;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.service.CompanyService;
import hu.cubix.hr.service.EmployeeMainService;
import hu.cubix.hr.service.InitDbService;

@SpringBootTest
@AutoConfigureTestDatabase
public class CompanyControllerIT {

	@Autowired
	CompanyService companyService;

	@Autowired
	private EmployeeMainService employeeService;

	@Autowired
	InitDbService initDbService;

	@Test
	void testAddEmployee() throws Exception {

		Company comp = companyService.save(new Company(444,"BB", "Bécs", null));

		String eName = "Jon";
		int eSalary = 12000;
		LocalDateTime entry = LocalDateTime.of(2009, 3,28,14,33,48);

		Employee emp = employeeService.create(new Employee(eName,eSalary,entry));

		Company returnedComp = companyService.addEmployee(comp.getId(), emp);

		Employee addedEmp = returnedComp.getEmployees().get(0);

		assertThat(comp.getId()).isEqualTo(returnedComp.getId());
		assertThat(returnedComp.getEmployees().size()).isEqualTo(1);
		assertThat(addedEmp.getId()).isEqualTo(emp.getId());
		assertThat(addedEmp.getCompany().getId()).isEqualTo(comp.getId());

	}

	@Test
	void testDeleteEmployee() throws Exception {

		Company comp = companyService.save(new Company(444,"BB", "Bécs", null));

		String eName = "Jon";
		int eSalary = 12000;
		LocalDateTime entry = LocalDateTime.of(2009, 3,28,14,33,48);

		Employee emp = employeeService.create(new Employee(eName,eSalary,entry));

		Company returnedComp = companyService.addEmployee(comp.getId(), emp);

		Company afterDelete = companyService.deleteEmployee(returnedComp.getId(),emp.getId());
		assertThat(returnedComp.getId()).isEqualTo(afterDelete.getId());
		assertThat(returnedComp.getEmployees().size()).isEqualTo(1);
		assertThat(afterDelete.getEmployees().size()).isEqualTo(0);

	}

	@Test
	void testReplaceEmployeeList() throws Exception {

		Company comp = companyService.save(new Company(444,"BB", "Bécs", null));

		String eName = "Jon";
		int eSalary = 12000;
		LocalDateTime entry = LocalDateTime.of(2009, 3,28,14,33,48);

		Employee emp = employeeService.create(new Employee(eName,eSalary,entry));

		companyService.addEmployee(comp.getId(), emp);

		Employee kim = employeeService.create(new Employee("Kim",100000,entry));

		Employee jan = employeeService.create(new Employee("Jan",250000,entry));

		List<Employee> employees = new ArrayList<>(List.of(kim,jan));

		Company returnedComp = companyService.replaceEmployees(comp.getId(), employees);

		System.out.println(returnedComp.getEmployees());

		assertThat(returnedComp.getId()).isEqualTo(comp.getId());
		assertThat(returnedComp.getEmployees().size()).isEqualTo(2);
		assertThat(returnedComp.getEmployees()).containsExactlyInAnyOrder(kim, jan);

	}

}
