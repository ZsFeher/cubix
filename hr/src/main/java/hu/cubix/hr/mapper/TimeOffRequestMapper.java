package hu.cubix.hr.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.dto.TimeOffRequestDto;
import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.TimeOffRequest;

@Mapper(componentModel = "spring")
public interface TimeOffRequestMapper {

	public TimeOffRequest trDtoToTR(TimeOffRequestDto trDto);

	public TimeOffRequestDto trToTRDto(TimeOffRequest tr);
	public List<TimeOffRequestDto> trListToDto(List<TimeOffRequest> timeOffRequests);

	@Mapping(target = "company", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);

	@Mapping(target = "employees", ignore = true)
	public CompanyDto companyToDto(Company company);

	public Company dtoToCompany(CompanyDto companyDto);

}
