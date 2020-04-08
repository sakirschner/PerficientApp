package com.techelevator.Perficient.daos;

import java.util.List;
import java.util.UUID;

import com.techelevator.Perficient.models.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getAllEmployees();
	
	public void createEmployee(Employee employee);
	
	public Employee getEmployeeById(UUID employeeId);
	
	public void updateEmployeeById(Employee employee, UUID employeeId);
	
	public void deleteEmployeeById(UUID employeeId);
	
	public Employee getNewEmployee();
}
