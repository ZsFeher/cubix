package hu.cubix.hr.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;

import hu.cubix.hr.config.HRConfigurationProperties;
import hu.cubix.hr.config.HRConfigurationProperties.WorkingYears;
import hu.cubix.hr.config.HRConfigurationProperties.PayRaise;
import hu.cubix.hr.model.Employee;

//@Service
public class SmartEmployeeService extends ProfileEmployeeService {

	@Autowired
	private HRConfigurationProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		WorkingYears wyconf =  config.getWorkingYears();
		PayRaise prconf = config.getPayRaise();

		LocalDateTime now = LocalDateTime.now();
		long daysBetween = Duration.between( employee.getEntryDate(),now).toDays();
		System.out.println ("Days: " + daysBetween);

		if(daysBetween < wyconf.getFirstLevel()*365){
			return prconf.getGroundlevel();
		} else if(daysBetween < wyconf.getSecondLevel()*365){
			return prconf.getFirstlevel();
		} else if(daysBetween < wyconf.getThirdLevel()*365){
			return prconf.getSecondlevel();
		} else {
			return prconf.getThirdlevel();
		}
	}
}
