package hu.cubix.hr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hu.cubix.hr.model.Employee;

public class TimeOffRequestDto {

	private long id;

	private LocalDate startDate;

	private LocalDate endDate;

	private EmployeeDto relatedEmployee;

	private EmployeeDto approver;

	private LocalDateTime requestSentAt;

	private int statusCode;

	public TimeOffRequestDto(long id, LocalDate startDate, LocalDate endDate,EmployeeDto relatedEmployee, EmployeeDto approver, int statusCode) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.relatedEmployee = relatedEmployee;
		this.approver = approver;
		this.requestSentAt = LocalDateTime.now();
		this.statusCode = statusCode;
	}

	public TimeOffRequestDto(LocalDate startDate, LocalDate endDate,EmployeeDto relatedEmployee) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.relatedEmployee = relatedEmployee;
		this.requestSentAt = LocalDateTime.now();
		this.statusCode = 0;
	}

	public TimeOffRequestDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDateTime getRequestSentAt() {
		return requestSentAt;
	}

	public void setRequestSentAt(LocalDateTime requestSentAt) {
		this.requestSentAt = requestSentAt;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public EmployeeDto getRelatedEmployee() {
		return relatedEmployee;
	}
	public void setRelatedEmployee(EmployeeDto relatedEmployee) {
		this.relatedEmployee = relatedEmployee;
	}
	public EmployeeDto getApprover() {
		return approver;
	}
	public void setApprover(EmployeeDto approver) {
		this.approver = approver;
	}
}
