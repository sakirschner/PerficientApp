package com.techelevator.Perficient.controllers;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.Perficient.daos.AssignedToDAO;
import com.techelevator.Perficient.models.AssignedTo;

@RestController
@RequestMapping("/employees/{employeeId}")
@CrossOrigin
public class AssignedToController {

	@Autowired
	AssignedToDAO assignedDAO;
	
	@GetMapping("/assignment")
	public AssignedTo getManagerByEmployeeId(@PathVariable UUID employeeId) {
		logTimestamp();
		AssignedTo manager = assignedDAO.getManagerByEmployeeId(employeeId);
		return manager;
	}

	@PostMapping("/assignment/{managerId}")
	public void addAssignment(@PathVariable UUID managerId, @PathVariable UUID employeeId) {
		logTimestamp();
		assignedDAO.saveAssignment(managerId, employeeId);
	}
	
	@PutMapping("/assignment/{managerId}")
	public void updateAssignmentById(@PathVariable UUID managerId, @PathVariable UUID employeeId,
			@RequestBody AssignedTo assignment) {
		logTimestamp();
		assignedDAO.updateAssignment(managerId, employeeId, assignment);
	}
	
	@DeleteMapping("/assignment")
	public void deleteAssignmentById(@PathVariable UUID employeeId) {
		logTimestamp();
		assignedDAO.deleteAssignment(employeeId);
	}
	
	private static void logTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Request delivered from Perficient REST API at " + timestamp);
	}
}
