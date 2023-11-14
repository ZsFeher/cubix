package hu.cubix.hr.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import hu.cubix.hr.model.Position;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

public class EmployeeDto {

	private long id;

	@NotEmpty
	private String name;


	@NotEmpty
	private Position position;

	@Positive
	private int salary;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime entryDate;

	public EmployeeDto() {
	}

	public EmployeeDto(long id, String name, String job, int salary, LocalDateTime entryDate) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.entryDate = entryDate;
	}

	public long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public LocalDateTime getEntryDate() {
		return entryDate;
	}

	public void setId(long id) {
		this.id = id;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
