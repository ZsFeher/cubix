package hu.cubix.hr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	public EmployeeDto employeeToDto(Employee employee);

	public List<EmployeeDto> employeeListToDto(List<Employee> employees);

	public Employee dtoToEmployee(EmployeeDto employee);

}
