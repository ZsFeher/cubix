package hu.cubix.hr.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.hr.dto.EmployeeDto;
import hu.cubix.hr.dto.TimeOffRequestDto;
import hu.cubix.hr.mapper.TimeOffRequestMapper;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.TimeOffRequest;
import hu.cubix.hr.service.EmployeeMainService;
import hu.cubix.hr.service.TimeOffRequestService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/timeoffrequests")
public class TimeOffRequestController {

	@Autowired
	TimeOffRequestService timeOffRequestService;

	@Autowired
	EmployeeMainService employeeMainService;

	@Autowired
	TimeOffRequestMapper trMapper;

	@GetMapping
	public List<TimeOffRequestDto> listTimeOffRequests()
	{
		List<TimeOffRequest> timeOffRequests = timeOffRequestService.getAll();
		return trMapper.trListToDto(timeOffRequests);
	}

	@PostMapping
	public TimeOffRequestDto sendTORequest(@RequestBody TimeOffRequestDto timeOffRequestDto, @RequestParam int relEmployeeId)
	{
		TimeOffRequest timeOffRequest = trMapper.trDtoToTR(timeOffRequestDto);

		Employee relEmployee = findEmployeeByIdOrThrow(relEmployeeId);
		timeOffRequest.setRelatedEmployee(relEmployee);
		timeOffRequest.setStatusCode(0);

		timeOffRequestService.send(timeOffRequest);

		return trMapper.trToTRDto(timeOffRequest);
	}

	@PutMapping("/{id}")
	public TimeOffRequestDto updateTORequest(@PathVariable long id, @RequestBody TimeOffRequestDto timeOffRequestDto)
	{
		TimeOffRequest existingTR = findByIdOrThrow(id);

		TimeOffRequest timeOffRequest = trMapper.trDtoToTR(timeOffRequestDto);

		if(timeOffRequest.getStatusCode() > 0){ //already judged, cannot be modified
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
		}

		TimeOffRequest updated = timeOffRequestService.update(timeOffRequest);
		return trMapper.trToTRDto(updated);
	}

	@DeleteMapping("/{id}")
	public void deleteTORequest(@PathVariable long id)
	{
		timeOffRequestService.delete(id);
	}

	@PutMapping("/approveOrDecline/{id}")
	public TimeOffRequestDto approveOrDeclineTO(@PathVariable long id, @RequestParam int approverId, @RequestParam int statusCode)
	{
		TimeOffRequest existingTR = findByIdOrThrow(id);
		existingTR.setStatusCode(statusCode);

		Employee approver = findEmployeeByIdOrThrow(approverId);
		existingTR.setApprover(approver);

		TimeOffRequest updated = timeOffRequestService.update(existingTR);

		return trMapper.trToTRDto(updated);

	}

	@GetMapping("/search")
	public List<TimeOffRequest> searchForTO(@RequestBody TimeOffRequest timeOffRequest, @RequestParam LocalDateTime from, @RequestParam LocalDateTime to)
	{
		List<TimeOffRequest> tos = timeOffRequestService.searchTO(timeOffRequest,from,to);
		return tos;
	}

	private Employee findEmployeeByIdOrThrow(long id) {
		return employeeMainService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	private TimeOffRequest findByIdOrThrow(long id) {
		return timeOffRequestService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
