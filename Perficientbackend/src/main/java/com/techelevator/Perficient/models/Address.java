package com.techelevator.Perficient.models;

import java.util.UUID;

public class Address {

	private UUID addressId;
	private UUID employeeId;
	private String street;
	private String suite;
	private String city;
	private String region;
	private String postal;
	private String country;
	
	public UUID getAddressId() {
		return addressId;
	}
	
	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}
	
	public UUID getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getSuite() {
		return suite;
	}
	
	public void setSuite(String suite) {
		this.suite = suite;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getPostal() {
		return postal;
	}
	
	public void setPostal(String postal) {
		this.postal = postal;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}
