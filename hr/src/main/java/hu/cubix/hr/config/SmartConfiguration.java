package hu.cubix.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.cubix.hr.service.ProfileEmployeeService;
import hu.cubix.hr.service.SmartEmployeeService;
import hu.cubix.hr.service.EmployeeService;

@Configuration
@Profile("smart")
public class SmartConfiguration {

	@Bean
	public ProfileEmployeeService employeeService()
	{
		return new SmartEmployeeService();
	}

}
