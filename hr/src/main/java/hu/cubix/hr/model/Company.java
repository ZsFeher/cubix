package hu.cubix.hr.model;

import java.util.List;

import hu.cubix.hr.dto.EmployeeDto;

public class Company {

	private long id;
	private long regNum;
	private String name;
	private String address;
	private List<EmployeeDto> employees;

	public Company()
	{
	}

	public Company(long id, long regNum, String name, String address, List<EmployeeDto> employees) {
		this.id = id;
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

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
}
