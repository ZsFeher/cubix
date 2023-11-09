package hu.cubix.hr.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.JobAndAverageSalary;

public interface CompanyRepository extends JpaRepository<Company,Long> {

	@Query(value = "SELECT c FROM Company c JOIN c.employees e WHERE e.salary > :salary")
	List<Company> findCompaniesWithSalaryLimit(@Param("salary") int salary);

	@Query(value = "SELECT c FROM Company c JOIN c.employees e GROUP BY c HAVING COUNT(e) > :limit")
	List<Company> findByEmployeeExceedingNum(@Param("limit") int limit);

	@Query(value = "SELECT e.job as job,AVG(e.salary) as averageSalary FROM Employee e WHERE e.company.id = :companyId GROUP BY e.job ORDER BY AVG(e.salary) DESC")
	List<JobAndAverageSalary> listAverageSalariesByJob(@Param("companyId") long companyId);

	Page<Company> findAll(Pageable pageable);

}
