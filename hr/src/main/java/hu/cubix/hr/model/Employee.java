package hu.cubix.hr.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.Nullable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

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

	//private String job;
	@ManyToOne
	@Nullable
	private Position position;
	private int salary;

	@ManyToOne
	private Company company;

	private String username;

	private String password;

	@ManyToOne
	private Employee manager;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime entryDate;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> roles;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Employee()
	{
	}

	public Employee(long id, String name, int salary, LocalDateTime entryDate ) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.entryDate = entryDate;
	}

	public Employee(String name, int salary, LocalDateTime entryDate ) {
		this.name = name;
		this.salary = salary;
		this.entryDate = entryDate;
	}

	public Employee(String name, int salary, LocalDateTime entryDate, String username, String password ) {
		this.name = name;
		this.salary = salary;
		this.entryDate = entryDate;
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Employee employee = (Employee) o;
		return id == employee.id && salary == employee.salary && Objects.equals(name, employee.name) && Objects.equals(position, employee.position) && Objects.equals(company, employee.company) && Objects.equals(entryDate, employee.entryDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, position, salary, company, entryDate);
	}
}
