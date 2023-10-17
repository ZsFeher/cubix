package hu.cubix.hr.service;


import hu.cubix.hr.model.Employee;

//@Service
public class DefaultEmployeeService extends ProfileEmployeeService{

	@Override
	public int getPayRaisePercent(Employee employee) {

		return 5;
	}
}
