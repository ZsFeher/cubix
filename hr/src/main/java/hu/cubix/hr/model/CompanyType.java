package hu.cubix.hr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
/*
enum CompanyType {
	LimitedPartnership, LLC,  Corporation;
}
*/

@Entity
public class CompanyType {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	public CompanyType()
	{
	}

	public CompanyType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
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
}
