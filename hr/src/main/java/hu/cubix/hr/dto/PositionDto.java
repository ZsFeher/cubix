package hu.cubix.hr.dto;

import hu.cubix.hr.model.Qualification;

public class PositionDto {

	private String name;

	public PositionDto() {

	}

	public PositionDto(String name) {

		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
