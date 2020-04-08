package com.techelevator.Perficient.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.Perficient.daos.AddressDAO;
import com.techelevator.Perficient.daos.AssignedToDAO;
import com.techelevator.Perficient.daos.EmployeeBusinessUnitRoleDAO;
import com.techelevator.Perficient.daos.EmployeeDAO;
import com.techelevator.Perficient.models.Address;
import com.techelevator.Perficient.models.AssignedTo;
import com.techelevator.Perficient.models.Employee;
import com.techelevator.Perficient.models.EmployeeBusinessUnitRole;

import java.sql.Timestamp;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;
	@Autowired
	AddressDAO addressDAO;
	@Autowired
	EmployeeBusinessUnitRoleDAO roleDAO;
	@Autowired
	AssignedToDAO assignedDAO;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		logTimestamp();
		List<Employee> allEmployees = employeeDAO.getAllEmployees();
		for(Employee employee: allEmployees) {
			UUID employeeId = employee.getEmployeeId();
			Address address = addressDAO.getAddressByEmployeeId(employeeId);
			EmployeeBusinessUnitRole role = roleDAO.getRoleByEmployeeId(employeeId);
			AssignedTo manager = assignedDAO.getManagerByEmployeeId(employeeId);
			employee.setAddress(address);
			employee.setRole(role);
			employee.setAssignment(manager);
		}
		return allEmployees;
	}

	@PostMapping("/employees")
	public void createEmployee(@RequestBody Employee employee) {
		logTimestamp();
		employeeDAO.createEmployee(employee);
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById(@PathVariable UUID employeeId) {
		logTimestamp();
		Employee theEmployee = employeeDAO.getEmployeeById(employeeId);
		Address address = addressDAO.getAddressByEmployeeId(employeeId);
		EmployeeBusinessUnitRole role = roleDAO.getRoleByEmployeeId(employeeId);
		AssignedTo manager = assignedDAO.getManagerByEmployeeId(employeeId);
		theEmployee.setAddress(address);
		theEmployee.setRole(role);
		theEmployee.setAssignment(manager);
		return theEmployee;
	}
	
	@PutMapping("/employees/{employeeId}")
	public void updateEmployeeById(@RequestBody Employee employee, @PathVariable UUID employeeId) {
		logTimestamp();
		employeeDAO.updateEmployeeById(employee, employeeId);
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public void deleteEmployeeById(@PathVariable UUID employeeId) {
		logTimestamp();
		employeeDAO.deleteEmployeeById(employeeId);
	}
	
	@GetMapping("/employees/new")
	public Employee getNewEmployee() {
		logTimestamp();
		Employee newEmployee = employeeDAO.getNewEmployee();
		return newEmployee;
	}
	
	private static void logTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Request delivered from Perficient REST API at " + timestamp);
	}
}
