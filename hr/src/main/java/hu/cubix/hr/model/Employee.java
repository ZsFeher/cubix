package hu.cubix.hr.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

@NamedQuery(
		name = "Employee.count",
		query = "SELECT COUNT(e) FROM Employee e"
)
@Entity
public class Employee {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String job;
	private int salary;

	@ManyToOne
	private Company company;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime entryDate;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Employee()
	{
	}

	public Employee(long id, String name, String job, int salary, LocalDateTime entryDate ) {
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

	public String toString()
	{
		return name;
	}

}
