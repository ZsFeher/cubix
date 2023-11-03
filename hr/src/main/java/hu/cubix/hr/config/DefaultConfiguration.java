package hu.cubix.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.cubix.hr.service.DefaultEmployeeService;
import hu.cubix.hr.service.EmployeeMainService;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {

	@Bean
	public EmployeeMainService employeeService()
	{
		return new DefaultEmployeeService();
	}

}
