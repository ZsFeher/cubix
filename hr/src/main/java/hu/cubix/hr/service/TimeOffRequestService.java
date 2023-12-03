package hu.cubix.hr.service;

import static hu.cubix.hr.service.TimeOffRequestSpecification.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.hr.model.Company;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.HrUser;
import hu.cubix.hr.model.Position;
import hu.cubix.hr.model.TOStatus;
import hu.cubix.hr.model.TimeOffRequest;
import hu.cubix.hr.repositories.TimeOffRequestRepository;
import jakarta.transaction.Transactional;

@Service
public class TimeOffRequestService {

	@Autowired
	TimeOffRequestRepository timeOffRequestRepository;

	public List<TimeOffRequest> getAll() {
		return timeOffRequestRepository.findAll();
	}

	public Optional<TimeOffRequest> findById(long id) {
		return timeOffRequestRepository.findById(id);
	}

	@Transactional
	public TimeOffRequest send(TimeOffRequest timeOffRequest)
	{
		return timeOffRequestRepository.save(timeOffRequest);
	}

	@Transactional
	public TimeOffRequest update(TimeOffRequest timeOffRequest)
	{
		if (!timeOffRequestRepository.existsById(timeOffRequest.getId())) {
			return null;
		} else {
			return timeOffRequestRepository.save(timeOffRequest);
		}
	}
	@Transactional
	public void delete(long id)
	{
			timeOffRequestRepository.deleteById(id);
	}

	public List<TimeOffRequest> searchTO(TimeOffRequest example, LocalDateTime from, LocalDateTime to)
	{
		TOStatus statusCode = example.getStatusCode();
		String relatedEmployeeName = example.getRelatedEmployee() != null ? example.getRelatedEmployee().getName() : null;
		String approverName = example.getApprover() != null ? example.getApprover().getName() : null;
		LocalDateTime sentAt = example.getRequestSentAt();
		LocalDate startDate = example.getStartDate();
		LocalDate endDate = example.getEndDate();

		Specification<TimeOffRequest> specification = Specification.where(null);

		if(statusCode != null){
			specification = specification.and(statusEquals(statusCode));
		}

		if(StringUtils.hasText(relatedEmployeeName)){
			specification = specification.and(nameStartsWith(relatedEmployeeName));
		}

		if(StringUtils.hasText(approverName)){
			specification = specification.and(approverStartsWith(approverName));
		}

		if(from != null && to != null){
			specification = specification.and(sentBetween(from,to));
		}

		if(startDate != null && endDate != null){
			specification = specification.and(timeOffInterval(startDate,endDate));
		}

		return timeOffRequestRepository.findAll(specification, Sort.by("id"));

	}

	@Transactional
	public TimeOffRequest approveOrDecline(long id, TOStatus status)
	{
		TimeOffRequest existingTR = findById(id).get();

		HrUser currentUser = (HrUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if(existingTR.getRelatedEmployee().getManager().getId() != currentUser.getEmployee().getId()){
			throw new AccessDeniedException("Only the manager can approve or decline TimeOffRequests");
		}

		existingTR.setStatusCode(status);
		existingTR.setApprover(currentUser.getEmployee());

		timeOffRequestRepository.save(existingTR);
		return existingTR;
	}

}
