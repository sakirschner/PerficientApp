package com.techelevator.Perficient;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.Perficient.daos.JDBCEmployeeDAO;
import com.techelevator.Perficient.daos.JDBCSkillDAO;

import com.techelevator.Perficient.models.Employee;

public class DAOintegrationTests {
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	private static SingleConnectionDataSource dataSource;
	private JDBCEmployeeDAO employeeDao;
	private JDBCSkillDAO skillDao;
	
	@BeforeClass
	public static void setupDatasource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/perficient");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		employeeDao = new JDBCEmployeeDAO(dataSource);
		skillDao = new JDBCSkillDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	private Employee getEmployee(UUID employeeId, String firstName, String lastName,
			String contactEmail, String companyEmail, String birthDate, String hiredDate) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setContactEmail(contactEmail);
		employee.setCompanyEmail(companyEmail);
		employee.setBirthDate(birthDate);
		employee.setHiredDate(hiredDate);
		return employee;
	}
	
	@Test
	public void test_getAllEmployees_insert_employee_returns_new_employee() {
		List<Employee> results = employeeDao.getAllEmployees();
		int startNum = results.size();
		Employee theEmployee = getEmployee(UUID.fromString("fb436013-e23f-455c-b77f-55f9a4e0e245"),"Scott","Kirschner",
			"test@test.com", "test@test.com", "04/14/1991", "04/07/2020");
		employeeDao.createEmployee(theEmployee);
		List<Employee> results2 = employeeDao.getAllEmployees();
		int endNum = results2.size();
		assertNotNull(results);
		assertNotNull(results2);
		assertEquals(startNum + 1, endNum);
	}
	
}

