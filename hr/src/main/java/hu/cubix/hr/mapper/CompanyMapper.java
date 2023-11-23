package hu.cubix.hr.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	@Mapping(target = "employees", ignore = true)
	public CompanyDto companyToDto(Company company);

	public Company dtoToCompany(CompanyDto companyDto);

	public List<CompanyDto> companyListToDto(List<Company> companies);

	public List<Company> dtoListToCompany(List<CompanyDto> companies);

	@Mapping(target = "company", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);

}
