package hu.cubix.hr.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;


public class Employee {

	private long id;
	private String name;
	private String job;
	private int salary;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime entryDate;

	public Employee()
	{
	}

	public Employee(long id, String name, String job, int salary, LocalDateTime entryDate) {
		this.id = id;
		this.name = name;
		this.job = job;
		this.salary = salary;
		this.entryDate = entryDate;
	}

	public long getId() {
		return id;
	}

	public String getJob() {

		return job;
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

	public void setJob(String job)
	{
		this.job = job;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	public void setEntryDate(LocalDateTime entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
