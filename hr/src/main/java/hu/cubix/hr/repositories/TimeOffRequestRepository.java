package hu.cubix.hr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.TimeOffRequest;

public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest,Long>, JpaSpecificationExecutor<TimeOffRequest> {



}
