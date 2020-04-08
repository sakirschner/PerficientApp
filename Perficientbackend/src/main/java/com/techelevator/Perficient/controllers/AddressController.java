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

import com.techelevator.Perficient.daos.AddressDAO;
import com.techelevator.Perficient.models.Address;

@RestController
@RequestMapping("/employees/{employeeId}")
@CrossOrigin
public class AddressController {

	@Autowired
	AddressDAO addressDAO;
	
	@GetMapping("/address")
	public Address getAddressByEmployeeId(@PathVariable UUID employeeId) {
		logTimestamp();
		Address address = addressDAO.getAddressByEmployeeId(employeeId);
		return address;
	}

	@PostMapping("/address")
	public void addAddress(@RequestBody Address address, @PathVariable UUID employeeId) {
		logTimestamp();
		addressDAO.saveAddress(address, employeeId);
	}
	
	@PutMapping("/address")
	public void updateAddressByEmployeeId(@RequestBody Address address, @PathVariable UUID employeeId) {
		logTimestamp();
		addressDAO.updateAddressByEmployeeId(address, employeeId);
	}
	
	@DeleteMapping("/address")
	public void deleteFieldByEmployeeId(@PathVariable UUID employeeId) {
		logTimestamp();
		addressDAO.deleteAddressByEmployeeId(employeeId);
	}
	
	private static void logTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Request delivered from Perficient REST API at " + timestamp);
	}
	
}
