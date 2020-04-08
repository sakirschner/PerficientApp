package com.techelevator.Perficient.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Perficient.models.Address;
import com.techelevator.Perficient.models.Employee;
import com.techelevator.Perficient.models.EmployeeBusinessUnitRole;

@Component
public class JDBCEmployeeDAO implements EmployeeDAO {
	
	private JdbcTemplate employeeTemplate;
	
	//creates database connection
	public JDBCEmployeeDAO(DataSource datasource) {
		employeeTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		String sql = "SELECT * FROM employee";
		SqlRowSet results = employeeTemplate.queryForRowSet(sql);
		while(results.next()) {
			Employee retrievedEmployee = new Employee();
			retrievedEmployee.setEmployeeId((UUID)results.getObject("employee_id"));
			retrievedEmployee.setFirstName(results.getString("first_name"));
			retrievedEmployee.setLastName(results.getString("last_name"));
			retrievedEmployee.setContactEmail(results.getString("contact_email"));
			retrievedEmployee.setBirthDate(results.getString("birth_date"));
			retrievedEmployee.setHiredDate(results.getString("hired_date"));
			retrievedEmployee.setCompanyEmail(results.getString("company_email"));
			allEmployees.add(retrievedEmployee);
		}
		return allEmployees;
	}

	@Override
	public void createEmployee(Employee employee) {
		String sql = "INSERT INTO employee (first_name, last_name, contact_email, " 
				+ "company_email, birth_date, hired_date) VALUES (?,?,?,?,?,?)";
		employeeTemplate.update(sql,
				employee.getFirstName(),
				employee.getLastName(),
				employee.getContactEmail(),
				employee.getCompanyEmail(),
				employee.getBirthDate(),
				employee.getHiredDate());
	}

	@Override
	public Employee getEmployeeById(UUID employeeId) {
		Employee retrievedEmployee = new Employee();
		String sql = "SELECT * FROM employee "
				+ "WHERE (employee.employee_id = ?)";
		SqlRowSet result = employeeTemplate.queryForRowSet(sql, employeeId);
		if(result.next()) {
			retrievedEmployee = mapResultToEmployee(result);
		} else {
			//come back for exception
			System.out.println("Empoyee not found");
		}
		return retrievedEmployee;
	}

	@Override
	public void updateEmployeeById(Employee employee, UUID employeeId) {

		String sql = "UPDATE employee SET (first_name, last_name, contact_email, "
				+ "company_email, birth_date, hired_date) = " 
				+ "(?,?,?,?,?,?) WHERE (employee_id = ?)";
		employeeTemplate.update(sql,
				employee.getFirstName(),
				employee.getLastName(),
				employee.getContactEmail(),
				employee.getCompanyEmail(),
				employee.getBirthDate(),
				employee.getHiredDate(),
				employeeId);
	}

	@Override
	public void deleteEmployeeById(UUID employeeId) {
		String sql = "DELETE FROM employee WHERE (employee_id = ?)";
		employeeTemplate.update(sql, employeeId);

	}
	
	@Override
	public Employee getNewEmployee() {
		Employee retrievedEmployee = new Employee();
		String sql = "SELECT * FROM employee " + 
				"ORDER BY created_at DESC " + 
				"LIMIT 1";
		SqlRowSet result = employeeTemplate.queryForRowSet(sql);
		if(result.next()) {
			retrievedEmployee.setEmployeeId((UUID)result.getObject("employee_id"));
			retrievedEmployee.setFirstName(result.getString("first_name"));
			retrievedEmployee.setLastName(result.getString("last_name"));
			retrievedEmployee.setContactEmail(result.getString("contact_email"));
			retrievedEmployee.setBirthDate(result.getString("birth_date"));
			retrievedEmployee.setHiredDate(result.getString("hired_date"));
			retrievedEmployee.setCompanyEmail(result.getString("company_email"));
		} else {
			//come back for exception
			System.out.println("Empoyee not found");
		}
		return retrievedEmployee;
	}
 	
	private Employee mapResultToEmployee(SqlRowSet result) {
		Employee retrievedEmployee = new Employee();
		retrievedEmployee.setEmployeeId((UUID)result.getObject("employee_id"));
		retrievedEmployee.setFirstName(result.getString("first_name"));
		retrievedEmployee.setLastName(result.getString("last_name"));
		retrievedEmployee.setContactEmail(result.getString("contact_email"));
		retrievedEmployee.setBirthDate(result.getString("birth_date"));
		retrievedEmployee.setHiredDate(result.getString("hired_date"));
		retrievedEmployee.setCompanyEmail(result.getString("company_email"));

		return retrievedEmployee;
	}

}
