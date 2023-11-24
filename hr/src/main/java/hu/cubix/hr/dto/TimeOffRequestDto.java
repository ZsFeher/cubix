package hu.cubix.hr.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import hu.cubix.hr.model.Employee;

public class TimeOffRequestDto {

	private long id;

	private LocalDate startDate;

	private LocalDate endDate;

	private Employee relatedEmployee;

	private Employee approver;

	private LocalDateTime requestSentAt;

	private int statusCode;

	public TimeOffRequestDto(long id, LocalDate startDate, LocalDate endDate,Employee relatedEmployee, Employee approver, int statusCode) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.relatedEmployee = relatedEmployee;
		this.approver = approver;
		this.requestSentAt = LocalDateTime.now();
		this.statusCode = statusCode;
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

	public Employee getRelatedEmployee() {
		return relatedEmployee;
	}
	public void setRelatedEmployee(Employee relatedEmployee) {
		this.relatedEmployee = relatedEmployee;
	}
	public Employee getApprover() {
		return approver;
	}
	public void setApprover(Employee approver) {
		this.approver = approver;
	}
}
