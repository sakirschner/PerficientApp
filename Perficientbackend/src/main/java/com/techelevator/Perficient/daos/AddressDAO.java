package com.techelevator.Perficient.daos;

import java.util.UUID;

import com.techelevator.Perficient.models.Address;

public interface AddressDAO {
	
	public Address getAddressByEmployeeId(UUID employeeId);
	
	public void saveAddress(Address address, UUID employeeId);
	
	public void updateAddressByEmployeeId(Address address, UUID employeeId);
	
	public void deleteAddressByEmployeeId(UUID employeeId);
}
