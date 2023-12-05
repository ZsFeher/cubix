package hu.cubix.hr.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.JobAndAverageSalary;
import hu.cubix.hr.model.NameAndId;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

	List<Employee> findByPositionName(String job);

	List<Employee> findByNameStartsWithIgnoreCase(String namePart);

	List<Employee> findAllByEntryDateBetween(LocalDateTime from, LocalDateTime to);

	List<Employee> findBySalaryGreaterThan(Integer minSalary);

	Employee findByUsername(String name);

	@Query(value = "SELECT e.name AS name,e.id AS id FROM Employee e WHERE e.manager.id =:employeeId")
	List<NameAndId> getManagedEmployees(@Param("employeeId") long employeeId);

}
