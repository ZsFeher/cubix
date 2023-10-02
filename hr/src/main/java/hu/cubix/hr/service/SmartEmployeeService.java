package hu.cubix.hr.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;

import hu.cubix.hr.config.HRConfigurationProperties;
import hu.cubix.hr.config.HRConfigurationProperties.WorkingYears;
import hu.cubix.hr.config.HRConfigurationProperties.PayRaise;
import hu.cubix.hr.model.Employee;

//@Service
public class SmartEmployeeService implements EmployeeService {

	@Autowired
	private HRConfigurationProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		WorkingYears wyconf =  config.getWorkingYears();
		PayRaise prconf = config.getPayRaise();

		LocalDate now = LocalDate.now();
		Period period = Period.between(employee.getEntryDate().toLocalDate(), now);
		long yearsPassed = period.getYears();

		if(yearsPassed < wyconf.getFirstLevel()){
			return prconf.getGroundlevel();
		} else if(yearsPassed < wyconf.getSecondLevel()){
			return prconf.getFirstlevel();
		} else if(yearsPassed < wyconf.getThirdLevel()){
			return prconf.getSecondlevel();
		} else {
			return prconf.getThirdlevel();
		}
	}
}
