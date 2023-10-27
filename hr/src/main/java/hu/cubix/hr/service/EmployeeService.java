package hu.cubix.hr.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.model.Employee;

public interface EmployeeService {

	public int getPayRaisePercent(Employee employee);

}
