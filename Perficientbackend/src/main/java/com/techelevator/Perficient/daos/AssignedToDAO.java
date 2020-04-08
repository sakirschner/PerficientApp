package com.techelevator.Perficient.daos;

import java.util.UUID;

import com.techelevator.Perficient.models.AssignedTo;


public interface AssignedToDAO {
	
	public AssignedTo getManagerByEmployeeId(UUID employeeId);
	
	public void saveAssignment(UUID managerId, UUID employeeId);
	
	public void updateAssignment(UUID managerId, UUID employeeId, AssignedTo assignment);
	
	public void deleteAssignment(UUID employeeId);

}
