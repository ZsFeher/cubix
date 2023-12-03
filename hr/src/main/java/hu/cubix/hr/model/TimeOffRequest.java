package hu.cubix.hr.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class TimeOffRequest {

	@Id
	@GeneratedValue
	private Long id;

	@FutureOrPresent
	private LocalDate startDate;

	@Future
	private LocalDate endDate;

	@ManyToOne
	private Employee relatedEmployee;

	@ManyToOne
	private Employee approver;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime requestSentAt;

	private TOStatus status;

	public TimeOffRequest(LocalDate startDate, LocalDate endDate, Employee relatedEmployee, Employee approver, TOStatus status) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.relatedEmployee = relatedEmployee;
		this.approver = approver;
		this.requestSentAt = LocalDateTime.now();
		this.status = status;
	}

	public TimeOffRequest() {
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

	public LocalDateTime getRequestSentAt() {
		return requestSentAt;
	}

	public void setRequestSentAt(LocalDateTime requestSentAt) {
		this.requestSentAt = LocalDateTime.now();
	}

	public TOStatus getStatusCode() {
		return status;
	}

	public void setStatusCode(TOStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TimeOffRequest{" +
				"id=" + id +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", relatedEmployee=" + relatedEmployee +
				", approver=" + approver +
				", requestSentAt=" + requestSentAt +
				", status=" + status +
				'}';
	}
}
