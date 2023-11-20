package hu.cubix.hr.service;

import static hu.cubix.hr.service.EmployeeSpecification.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.Position;
import hu.cubix.hr.repositories.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public abstract class EmployeeMainService implements EmployeeService {

	/*
	@PersistenceContext
	EntityManager em;

	private Map<Long, Employee> employees = new HashMap<>();
	{

		employees.put(1L,new Employee(1,"Nicholas","programmer",10000, LocalDateTime.of(2009, 3,28,14,33,48)));

	}
	*/

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Transactional
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Transactional
	public Employee create(Employee employee) {
		return save(employee);
	}

	@Transactional
	public Employee update(Employee employee) {
		if (!employeeRepository.existsById(employee.getId())) {
			return null;
		} else {
			return employeeRepository.save(employee);
		}
	}

	public List<Employee> searchEmployee(Employee example)
	{
		long id = example.getId();
		String name = example.getName();
		int salary = example.getSalary();
		Position position = example.getPosition();
		LocalDateTime entry = example.getEntryDate();
		Company company = example.getCompany();

		Specification<Employee> specification = Specification.where(null);

		if(id > 0){
			specification = specification.and(hasId(id));
		}

		if(StringUtils.hasText(name)){
			specification = specification.and(nameStartsWith(name));
		}

		if(position != null){
			String posName = position.getName();
			specification = specification.and(positionEquals(posName));
		}

		if(salary > 0){
			specification = specification.and(salaryApprox(salary));
		}

		if(entry != null){
			specification = specification.and(entryDateBetween(entry.toLocalDate()));
		}

		if(company != null){
			String companyName = company.getName();
			specification = specification.and(companyStartsWith(companyName));
		}

		return employeeRepository.findAll(specification);

	}




}
