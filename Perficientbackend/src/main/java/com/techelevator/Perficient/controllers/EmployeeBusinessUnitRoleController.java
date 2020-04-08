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

import com.techelevator.Perficient.daos.EmployeeBusinessUnitRoleDAO;
import com.techelevator.Perficient.models.EmployeeBusinessUnitRole;

@RestController
@RequestMapping("/employees/{employeeId}")
@CrossOrigin
public class EmployeeBusinessUnitRoleController {

	@Autowired
	EmployeeBusinessUnitRoleDAO roleDAO;
	
	@GetMapping("/role")
	public EmployeeBusinessUnitRole getRoleByEmployeeId(@PathVariable UUID employeeId) {
		logTimestamp();
		EmployeeBusinessUnitRole role = roleDAO.getRoleByEmployeeId(employeeId);
		return role;
	}

	@PostMapping("/role")
	public void addRole(@RequestBody EmployeeBusinessUnitRole role, @PathVariable UUID employeeId) {
		logTimestamp();
		roleDAO.saveRole(role, employeeId);
	}
	
	@PutMapping("/role")
	public void updateAddressByEmployeeId(@RequestBody EmployeeBusinessUnitRole role, @PathVariable UUID employeeId) {
		logTimestamp();
		roleDAO.updateRoleByEmployeeId(role, employeeId);
	}
	
	@DeleteMapping("/role")
	public void deleteFieldByEmployeeId(@PathVariable UUID employeeId) {
		logTimestamp();
		roleDAO.deleteRoleByEmployeeId(employeeId);
	}
	
	private static void logTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Request delivered from Perficient REST API at " + timestamp);
	}
	
}

