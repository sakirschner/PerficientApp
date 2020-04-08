package com.techelevator.Perficient.models;

import java.util.UUID;

public class Employee {

	private UUID employeeId;
	private String firstName;
	private String lastName;
	private String contactEmail;
	private String companyEmail;
	private String birthDate;
	private String hiredDate;
	private Address address;
	private EmployeeBusinessUnitRole role;
	private AssignedTo assignment;
	
	public UUID getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lasName) {
		this.lastName = lasName;
	}
	
	public String getContactEmail() {
		return contactEmail;
	}
	
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	
	public String getCompanyEmail() {
		return companyEmail;
	}
	
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getHiredDate() {
		return hiredDate;
	}
	
	public void setHiredDate(String hiredDate) {
		this.hiredDate = hiredDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public EmployeeBusinessUnitRole getRole() {
		return role;
	}
	
	public void setRole (EmployeeBusinessUnitRole role) {
		this.role = role;
	}
	
	public AssignedTo getAssignment() {
		return assignment;
	}
	
	public void setAssignment (AssignedTo assignment) {
		this.assignment = assignment;
	}
}
