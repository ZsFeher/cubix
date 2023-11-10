package hu.cubix.hr.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Position {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Qualification qualification;
	private int minSalary;

	public Position() {
	}

	public Position(String name, Qualification qualification, int minSalary) {
		this.name = name;
		this.qualification = qualification;
		this.minSalary = minSalary;
	}

	//@OneToMany(mappedBy = "position")
	//private List<Employee> employees;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nameOfPosition) {
		this.name = nameOfPosition;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public int getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
}
