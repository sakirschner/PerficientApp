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
import com.techelevator.Perficient.daos.JDBCFieldDAO;
import com.techelevator.Perficient.daos.JDBCSkillDAO;

import com.techelevator.Perficient.models.Employee;
import com.techelevator.Perficient.models.Field;
import com.techelevator.Perficient.models.Skill;

public class DAOintegrationTests {
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	private static SingleConnectionDataSource dataSource;
	private JDBCEmployeeDAO employeeDao;
	private JDBCSkillDAO skillDao;
	private JDBCFieldDAO fieldDao;
	
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
		fieldDao = new JDBCFieldDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	private Employee getEmployee(String firstName, String lastName,
			String contactEmail, String companyEmail, String birthDate, String hiredDate) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setContactEmail(contactEmail);
		employee.setCompanyEmail(companyEmail);
		employee.setBirthDate(birthDate);
		employee.setHiredDate(hiredDate);
		return employee;
	}
	
	private Skill getSkill(UUID employeeId, UUID fieldId, int experience) {
		Skill skill = new Skill();
		skill.setEmployeeId(employeeId);
		skill.setFieldId(fieldId);
		skill.setExperience(experience);
		return skill;
	}
	
	private Field getField(String name, String type) {
		Field field = new Field();
		field.setName(name);
		field.setType(type);
		return field;
	}
	
	@Test
	public void test_getAllEmployees_insert_employee_returns_new_employee() {
		List<Employee> results = employeeDao.getAllEmployees();
		int startNum = results.size();
		Employee theEmployee = getEmployee("Scott","Kirschner",
			"test@test.com", "test@test.com", "04/14/1991", "04/07/2020");
		employeeDao.createEmployee(theEmployee);
		List<Employee> results2 = employeeDao.getAllEmployees();
		int endNum = results2.size();
		assertEquals(startNum + 1, endNum);
	}
	
	@Test
	public void test_getAllSkillsByEmployeeId_insert_skill_returns_new_skill() {
		Employee theEmployee = getEmployee("Scott","Kirschner",
				"test@test.com", "test@test.com", "04/14/1991", "04/07/2020");
		employeeDao.createEmployee(theEmployee);
		Employee newEmployee = employeeDao.getNewEmployee();
		List<Skill> results = skillDao.getAllSkillsByEmployeeId(newEmployee.getEmployeeId());
		int startNum = results.size();
		Field theField = getField("Test", "Test");
		fieldDao.saveField(theField);
		Field newField = fieldDao.getNewField();
		Skill theSkill = getSkill(newEmployee.getEmployeeId(), newField.getFieldId(), 12);
		skillDao.addSkill(theSkill, newEmployee.getEmployeeId(), newField.getFieldId());
		List<Skill> results2 = skillDao.getAllSkillsByEmployeeId(newEmployee.getEmployeeId());
		int endNum = results2.size();
		assertEquals("Test", newField.getName());
		assertEquals(startNum + 1, endNum);
	}
}

