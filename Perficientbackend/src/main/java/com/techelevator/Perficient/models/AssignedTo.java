package com.techelevator.Perficient.models;

import java.util.UUID;

public class AssignedTo {
	
	private UUID assignedToId;
	private UUID superiorId;
	private UUID subordinateId;
	private String managerFirstName;
	private String managerLastName;
	
	public UUID getAssignedToId() {
		return assignedToId;
	}
	
	public void setAssignedToId(UUID assignedToId) {
		this.assignedToId = assignedToId;
	}
	
	public UUID getSuperiorId() {
		return superiorId;
	}
	
	public void setSuperiorId(UUID superiorId) {
		this.superiorId = superiorId;
	}
	
	public UUID getSubordinateId() {
		return subordinateId;
	}
	
	public void setSubordinateId(UUID subordinateId) {
		this.subordinateId = subordinateId;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}
}
