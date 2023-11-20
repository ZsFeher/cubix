package hu.cubix.hr.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue
	private long id;
	private long regNum;
	private String name;
	private String address;
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
	private List<Employee> employees;

	@ManyToOne
	private CompanyType companyType;

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public Company()
	{
	}

	public Company(long id, long regNum, String name, String address, List<Employee> employees) {
		this.id = id;
		this.regNum = regNum;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public Company(long regNum, String name, String address, List<Employee> employees) {
		this.regNum = regNum;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRegNum() {
		return regNum;
	}

	public void setRegNum(long regNum) {
		this.regNum = regNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Employee> getEmployees() {
		if(this.employees == null){
			this.employees = new ArrayList<>();
		}
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public void addEmployee(Employee employee)
	{
		employee.setCompany(this);
		getEmployees().add(employee);
	}

	public String toString(){
		return id+" "+name+" "+employees;
	}
}
