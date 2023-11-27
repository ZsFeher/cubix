package hu.cubix.hr;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.dto.TimeOffRequestDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TimeOffRequestControllerIT {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void testAddTOR() throws Exception {

		LocalDate startDate = LocalDate.of(2023,12,01);
		LocalDate endDate = LocalDate.of(2023,12,31);

		EmployeeDto employeeDto = new EmployeeDto("Greg", 100, LocalDateTime.of(2020,1,1,10,00));

		EmployeeDto createdEmployee = createEmployee(employeeDto);

		TimeOffRequestDto timeOffRequestDto = new TimeOffRequestDto(startDate,endDate,employeeDto);

		TimeOffRequestDto createdTO = createTORequest(timeOffRequestDto, createdEmployee.getId());

		assertThat(timeOffRequestDto.getStartDate()).isEqualTo(createdTO.getStartDate());
		assertThat(timeOffRequestDto.getEndDate()).isEqualTo(createdTO.getEndDate());
		assertThat(timeOffRequestDto.getRelatedEmployee().getName()).isEqualTo(createdTO.getRelatedEmployee().getName());
	}

	@Test
	void testApproveOrDecline() throws Exception {

		LocalDate startDate = LocalDate.of(2023,12,01);
		LocalDate endDate = LocalDate.of(2023,12,31);

		EmployeeDto greg = new EmployeeDto("Greg", 100, LocalDateTime.of(2020,1,1,10,00));

		EmployeeDto createdEmployee = createEmployee(greg);

		EmployeeDto dave = new EmployeeDto("Dave", 1000, LocalDateTime.of(2021,12,1,10,00));

		EmployeeDto createdEmployee2 = createEmployee(dave);

		TimeOffRequestDto timeOffRequestDto = new TimeOffRequestDto(startDate,endDate,greg);

		TimeOffRequestDto createdTO = createTORequest(timeOffRequestDto, createdEmployee.getId());

		TimeOffRequestDto approved = webTestClient
				.put()
				.uri(uriBuilder -> uriBuilder
						.path("/api/timeoffrequests/approveOrDecline/{id}")
						.queryParam("approverId", createdEmployee2.getId())
						.queryParam("statusCode", 1)
						.build(createdTO.getId()))
						.exchange().expectStatus().isOk().expectBody(TimeOffRequestDto.class).returnResult().getResponseBody();

		assertThat(approved.getStatusCode()).isEqualTo(1);
		assertThat(approved.getApprover().getName()).isEqualTo("Dave");
	}

	private TimeOffRequestDto createTORequest(TimeOffRequestDto timeOffRequestDto, long employeeId)
	{

		return webTestClient.post().uri(uriBuilder -> uriBuilder
			.path("/api/timeoffrequests")
			.queryParam("relEmployeeId", employeeId)
			.build())
				.bodyValue(timeOffRequestDto).exchange().expectStatus().isOk().expectBody(TimeOffRequestDto.class).returnResult().getResponseBody();

	}

	private EmployeeDto createEmployee(EmployeeDto employee)
	{
		return webTestClient.post().uri("/api/employees").bodyValue(employee).exchange().expectStatus().isOk().expectBody(EmployeeDto.class).returnResult().getResponseBody();
	}

}
