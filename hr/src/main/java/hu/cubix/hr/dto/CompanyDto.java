package hu.cubix.hr.dto;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyDto implements Cloneable {

	private long id;
	private long regNum;
	private String name;
	private String address;
	private List<EmployeeDto> employees;

	public CompanyDto()
	{
	}

	public CompanyDto(long id, long regNum, String name, String address, List<EmployeeDto> employees) {
		this.id = id;
		this.regNum = regNum;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public CompanyDto(long regNum, String name, String address, List<EmployeeDto> employees) {
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

	public CompanyDto copyWithoutEmployees() {

		CompanyDto companyDto1 = new CompanyDto();

		companyDto1.setId(this.getId());
		companyDto1.setName(this.getName());
		companyDto1.setAddress(this.getAddress());
		companyDto1.setRegNum(this.getRegNum());

		return companyDto1;
	}

	@Override
	public String toString() {
		return "CompanyDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
