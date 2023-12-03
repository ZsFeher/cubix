package hu.cubix.hr.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import hu.cubix.hr.model.Company_;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.Employee_;
import hu.cubix.hr.model.Position_;
import hu.cubix.hr.model.TOStatus;
import hu.cubix.hr.model.TimeOffRequest;
import hu.cubix.hr.model.TimeOffRequest_;

public class TimeOffRequestSpecification {

	public static Specification<TimeOffRequest> statusEquals(TOStatus statusCode)
	{
		return (root, cq, cb) -> cb.equal(root.get(TimeOffRequest_.status), statusCode);
	}

	public static Specification<TimeOffRequest> nameStartsWith(String name)
	{
		return (root, cq, cb) -> cb.like(root.get(TimeOffRequest_.relatedEmployee).get(Employee_.name), name + "%");
	}

	public static Specification<TimeOffRequest> approverStartsWith(String name)
	{
		return (root, cq, cb) -> cb.like(root.get(TimeOffRequest_.approver).get(Employee_.name), name + "%");
	}

	public static Specification<TimeOffRequest> sentBetween(LocalDateTime from,LocalDateTime to)
	{
		return (root, cq, cb) -> cb.between(root.get(TimeOffRequest_.requestSentAt), from,to);
	}

	public static Specification<TimeOffRequest> timeOffInterval(LocalDate from,LocalDate to)
	{
		return (root, cq, cb) -> cb.and(cb.greaterThanOrEqualTo(root.get(TimeOffRequest_.startDate), from),cb.lessThanOrEqualTo(root.get(TimeOffRequest_.endDate),to));
	}

}
