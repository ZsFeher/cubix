package hu.cubix.hr.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {


	@Mapping(target="company.employees", ignore = true)
	public EmployeeDto employeeToDto(Employee employee);

	public List<EmployeeDto> employeeListToDto(List<Employee> employees);
	@InheritInverseConfiguration
	public Employee dtoToEmployee(EmployeeDto employee);

	public List<Employee> dtoListToEmployee(List<EmployeeDto> employeeDtos);

}
