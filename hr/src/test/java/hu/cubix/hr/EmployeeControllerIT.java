package hu.cubix.hr;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void testEmployeeAddition()
	{
		List<EmployeeDto> employeesBefore = getEmployees();

		EmployeeDto employee = new EmployeeDto(10, "Mike", "Pilot", 15000, LocalDateTime.of(2019, 3,28,14,33,48));

		createEmployee(employee);

		List<EmployeeDto> employeesAfter = getEmployees();

		assertThat(employeesAfter.subList(0, employeesBefore.size()))
				.usingRecursiveFieldByFieldElementComparator()
				.containsExactlyElementsOf(employeesBefore);
		assertThat(employeesAfter.get(employeesAfter.size() - 1))
				.usingRecursiveComparison()
				.isEqualTo(employee);

	}

	@Test
	void testEmployeeAdditionBadEntryDate()
	{
		List<EmployeeDto> employeesBefore = getEmployees();

		EmployeeDto employee = new EmployeeDto(10, "Mike", "Pilot", 15000, LocalDateTime.of(2029, 3,28,14,33,48));

		createEmployee(employee);

		List<EmployeeDto> employeesAfter = getEmployees();

		assertThat(employeesAfter.subList(0, employeesBefore.size()))
				.usingRecursiveFieldByFieldElementComparator()
				.containsExactlyElementsOf(employeesBefore);
		assertThat(employeesAfter.get(employeesAfter.size() - 1))
				.usingRecursiveComparison()
				.isEqualTo(employee);
	}

	@Test
	void testEmployeeUpdate()
	{
		EmployeeDto employeeU = new EmployeeDto(1,"Nicholas","Chef",10000, LocalDateTime.of(2009, 3,28,14,33,48));

		EmployeeDto updatedEmployee = updateEmployee(1,employeeU);

		assertEquals(employeeU.getId(), updatedEmployee.getId());
		assertEquals(updatedEmployee.getJob(), "Chef");

	}

	@Test
	void testEmployeeUpdateBadId()
	{
		EmployeeDto employeeU = new EmployeeDto(3,"Jason","Manager",50000, LocalDateTime.of(2019, 3,28,14,33,48));

		EmployeeDto updatedEmployee = updateEmployee(3,employeeU);

		assertEquals(employeeU.getId(), updatedEmployee.getId());
		assertEquals(updatedEmployee.getJob(), "Manager");

	}

	private List<EmployeeDto> getEmployees()
	{
		List<EmployeeDto> allEmployees = webTestClient
				.get()
				.uri("/api/employees")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(EmployeeDto.class)
				.returnResult()
				.getResponseBody();

				Collections.sort(allEmployees, Comparator.comparing(employeeDto -> employeeDto.getId()));

				return allEmployees;
	}

	private void createEmployee(EmployeeDto employee)
	{

		webTestClient.post().uri("/api/employees").bodyValue(employee).exchange().expectStatus().isOk();

	}

	private EmployeeDto updateEmployee(int id, EmployeeDto employee)
	{

		return webTestClient.put().uri("/api/employees/{id}", id).bodyValue(employee).exchange().expectStatus().isOk().expectBody(EmployeeDto.class).returnResult().getResponseBody();
	}

}
