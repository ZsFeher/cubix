package hu.cubix.hr.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Company_;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.Employee_;
import hu.cubix.hr.model.Position_;

public class EmployeeSpecification {

	public static Specification<Employee> hasId(long id)
	{
		return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
	}

	public static Specification<Employee> nameStartsWith(String name)
	{
		return (root, cq, cb) -> cb.like(root.get(Employee_.name), name + "%");
	}

	public static Specification<Employee> positionEquals(String posName)
	{
		return (root, cq, cb) -> cb.equal(root.get(Employee_.position).get(Position_.name), posName);
	}

	public static Specification<Employee> salaryApprox(int salary)
	{
		double minSalary = salary*0.95;
		double maxSalary = salary*1.05;

		return (root, cq, cb) -> cb.between(cb.toDouble(root.get(Employee_.salary)), minSalary, maxSalary);
	}

	public static Specification<Employee> entryDateBetween(LocalDate entry)
	{
		return (root, cq, cb) -> cb.between(root.get(Employee_.entryDate), cb.literal(entry.atTime(LocalTime.MIDNIGHT)), cb.literal(entry.atTime(LocalTime.MAX)));
	}

	public static Specification<Employee> companyStartsWith(String name)
	{
		return (root, cq, cb) -> cb.like(root.get(Employee_.company).get(Company_.name), name + "%");
	}
}
