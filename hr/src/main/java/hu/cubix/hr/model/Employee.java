package hu.cubix.hr.model;

import java.time.LocalDateTime;


public class Employee {

	private long id;
	private String job;
	private int salary;
	private LocalDateTime entryDate;

	public Employee(long id, String job, int salary, LocalDateTime entryDate) {
		this.id = id;
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
}
