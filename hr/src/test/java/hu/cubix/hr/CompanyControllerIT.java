package hu.cubix.hr;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.mapper.CompanyMapper;
import hu.cubix.hr.mapper.EmployeeMapper;
import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.service.CompanyService;
import hu.cubix.hr.service.EmployeeMainService;
import hu.cubix.hr.service.InitDbService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CompanyControllerIT {

	@Autowired
	CompanyService companyService;

	@Autowired
	private EmployeeMainService employeeService;

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	CompanyMapper cMapper;

	@Autowired
	EmployeeMapper eMapper;

	@Test
	void testAddEmployee() throws Exception {

		CompanyDto comp = new CompanyDto(444,"SCC", "Pécs", null);

		CompanyDto createdComp = createCompany(comp);

		String eName = "Jon";
		int eSalary = 12000;
		LocalDateTime entry = LocalDateTime.of(2009, 3,28,14,33,48);

		EmployeeDto emp = new EmployeeDto(eName,eSalary,entry);

		webTestClient
			.put()
			.uri("/api/companies/addEmployee/{id}", createdComp.getId())
			.bodyValue(emp)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody(CompanyDto.class)
			.returnResult()
			.getResponseBody();

		Company returnedCompany = findByIdOrThrow(createdComp.getId());

		assertThat(createdComp.getId()).isEqualTo(returnedCompany.getId());
		assertThat(returnedCompany.getEmployees().size()).isEqualTo(1);
	}

	@Test
	void testDeleteEmployee() throws Exception {

		Company comp = new Company(444,"BB", "Bécs", null);

		String eName = "Jon";
		int eSalary = 12000;
		LocalDateTime entry = LocalDateTime.of(2009, 3,28,14,33,48);
		Employee emp = new Employee(eName,eSalary,entry);
		EmployeeDto createdEmp = createEmployee(eMapper.employeeToDto(emp));
		comp.addEmployee(emp);
		CompanyDto createdComp = createCompany(cMapper.companyToDto(comp));

		webTestClient
				.delete()
				.uri("/api/companies/deleteEmployee/{companyId}/{employeeId}", createdComp.getId(), createdEmp.getId())
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(CompanyDto.class)
				.returnResult()
				.getResponseBody();


		Company returnedCompany = findByIdOrThrow(createdComp.getId());

		assertThat(createdComp.getId()).isEqualTo(returnedCompany.getId());
		assertThat(returnedCompany.getEmployees().size()).isEqualTo(0);

	}

	@Test
	void testReplaceEmployeeList() throws Exception {

		Company comp = new Company(444,"BB", "Bécs", null);

		String eName = "Jon";
		int eSalary = 12000;
		LocalDateTime entry = LocalDateTime.of(2009, 3,28,14,33,48);
		Employee emp = new Employee(eName,eSalary,entry);

		EmployeeDto createdEmp = createEmployee(eMapper.employeeToDto(emp));
		comp.addEmployee(emp);
		CompanyDto createdComp = createCompany(cMapper.companyToDto(comp));

		String kName = "Konrad";
		EmployeeDto konrad = new EmployeeDto(kName,eSalary,entry);

		String vName = "Victor";
		EmployeeDto victor = new EmployeeDto(vName,eSalary,entry);

		List<EmployeeDto> employees = new ArrayList<>();
		employees.add(konrad);
		employees.add(victor);

		webTestClient
				.put()
				.uri("/api/companies/replaceEmployeeList/{id}", createdComp.getId())
				.bodyValue(employees)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(CompanyDto.class)
				.returnResult()
				.getResponseBody();

		Company returnedCompany = findByIdOrThrow(createdComp.getId());

		assertThat(createdComp.getId()).isEqualTo(returnedCompany.getId());
		assertThat(returnedCompany.getEmployees().size()).isEqualTo(2);

	}

	private EmployeeDto createEmployee(EmployeeDto employee)
	{

		return webTestClient.post().uri("/api/employees").bodyValue(employee).exchange().expectStatus().isOk().expectBody(EmployeeDto.class).returnResult().getResponseBody();

	}

	private CompanyDto createCompany(CompanyDto company)
	{

		return webTestClient.post().uri("/api/companies").bodyValue(company).exchange().expectStatus().isOk().expectBody(CompanyDto.class).returnResult().getResponseBody();

	}

	private Company findByIdOrThrow(long id) {
		return companyService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
