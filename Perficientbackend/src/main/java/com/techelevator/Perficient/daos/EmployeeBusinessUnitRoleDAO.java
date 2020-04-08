package com.techelevator.Perficient.daos;

import java.util.UUID;

import com.techelevator.Perficient.models.EmployeeBusinessUnitRole;


public interface EmployeeBusinessUnitRoleDAO {

	public EmployeeBusinessUnitRole getRoleByEmployeeId(UUID employeeId);
	
	public void saveRole(EmployeeBusinessUnitRole role, UUID employeeId);
	
	public void updateRoleByEmployeeId(EmployeeBusinessUnitRole role, UUID employeeId);
	
	public void deleteRoleByEmployeeId(UUID employeeId);
	
}
