package hu.cubix.hr.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.cubix.hr.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByJob(String job);

	List<Employee> findByNameStartsWithIgnoreCase(String namePart);

	List<Employee> findAllByEntryDateBetween(LocalDateTime from, LocalDateTime to);

	List<Employee> findBySalaryGreaterThan(Integer minSalary);

}
