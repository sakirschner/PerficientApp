package com.techelevator.Perficient.daos;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Perficient.models.Address;

@Component
public class JDBCAddressDAO implements AddressDAO {
	
	private JdbcTemplate addressTemplate;
	
	public JDBCAddressDAO(DataSource datasource) {
		addressTemplate = new JdbcTemplate(datasource); 
	}

	@Override
	public Address getAddressByEmployeeId(UUID employeeId) {
		Address address = new Address();
		String sql = "SELECT * FROM address WHERE (employee_id = ?)";
		SqlRowSet result = addressTemplate.queryForRowSet(sql, employeeId);
		if(result.next()) {
			address = mapResultToAddress(result);
		} else {
			//come back for exception
			System.out.println("Field not found");
		}
		return address;
	}

	@Override
	public void saveAddress(Address address, UUID employeeId) {
		String sql ="INSERT INTO address (employee_id, street, suite, city, region, postal, country) "
			+ "VALUES ((SELECT employee_id FROM employee WHERE employee_id = ?), ?,?,?,?,?,?)";
		addressTemplate.update(sql, employeeId,
				address.getStreet(),
				address.getSuite(),
				address.getCity(),
				address.getRegion(),
				address.getPostal(),
				address.getCountry());
	}

	@Override
	public void updateAddressByEmployeeId(Address address, UUID employeeId) {
		String sql = "UPDATE address SET (address_id, employee_id, street, suite, city, region, postal, country) " + 
				"= (?,(SELECT employee_id FROM employee WHERE employee_id = ?),?,?,?,?,?,?) " + 
				"WHERE employee_id = ?";
		addressTemplate.update(sql, 
				address.getAddressId(),
				employeeId,
				address.getStreet(),
				address.getSuite(),
				address.getCity(),
				address.getRegion(),
				address.getPostal(),
				address.getCountry(),
				employeeId);
	}

	@Override
	public void deleteAddressByEmployeeId(UUID employeeId) {
		String sql = "DELETE FROM address WHERE (employee_id = ?)";
		addressTemplate.update(sql, employeeId);

	}
	
	private Address mapResultToAddress(SqlRowSet result) {
		Address retrievedAddress = new Address();
		retrievedAddress.setAddressId((UUID)result.getObject("address_id"));
		retrievedAddress.setEmployeeId((UUID)result.getObject("employee_id"));
		retrievedAddress.setStreet(result.getString("street"));
		retrievedAddress.setSuite(result.getString("suite"));
		retrievedAddress.setCity(result.getString("city"));
		retrievedAddress.setRegion(result.getString("region"));
		retrievedAddress.setPostal(result.getString("postal"));
		retrievedAddress.setCountry(result.getString("country"));
		return retrievedAddress;
	}
}
