package hu.cubix.hr.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Position {

	@Id
	@GeneratedValue
	private int id;
	private String nameOfPosition;
	private String qualification;
	private int minSalary;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameOfPosition() {
		return nameOfPosition;
	}

	public void setNameOfPosition(String nameOfPosition) {
		this.nameOfPosition = nameOfPosition;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public int getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
}
