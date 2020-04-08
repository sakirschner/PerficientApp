package com.techelevator.Perficient.models;

import java.util.UUID;

public class EmployeeBusinessUnitRole {

	private UUID employeeBusinessUnitRoleId;
	private UUID employeeId;
	private int roleId;
	private int businessUnitId;
	private String roleName;
	private String businessUnitName;
	
	public UUID getEmployeeBusinessUnitRoleId() {
		return employeeBusinessUnitRoleId;
	}
	
	public void setEmployeeBusinessUnitRoleId(UUID employeeBusinessUnitRoleId) {
		this.employeeBusinessUnitRoleId = employeeBusinessUnitRoleId;
	}
	
	public UUID getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	
	public int getRoleId() {
		return roleId;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getBusinessUnitId() {
		return businessUnitId;
	}
	
	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitName() {
		businessUnitName = ""; 
		if (this.businessUnitId == 1) {
			businessUnitName = "Digital Experience Group";
		} else if (this.businessUnitId == 2) {
			businessUnitName = "Adobe";
		} else if (this.businessUnitId == 3) {
			businessUnitName = "IBM NBU";
		} else if (this.businessUnitId == 4) {
			businessUnitName = "API Management";
		}
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getRoleName() {
		roleName = "";
		if (this.roleId == 1) {
			roleName = "Technical Consultant";
		} else if (this.roleId == 2) {
			roleName = "Project Manager";
		} else if (this.roleId == 3) {
			roleName = "Director";
		} else if (this.roleId == 4) {
			roleName = "Chief";
		}
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
