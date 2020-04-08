package com.techelevator.Perficient.daos;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Perficient.models.EmployeeBusinessUnitRole;

@Component
public class JDBCEmployeeBusinessUnitRoleDAO implements EmployeeBusinessUnitRoleDAO {
	
	private JdbcTemplate roleTemplate;
	
	public JDBCEmployeeBusinessUnitRoleDAO(DataSource datasource) {
		roleTemplate = new JdbcTemplate(datasource); 
	}

	@Override
	public EmployeeBusinessUnitRole getRoleByEmployeeId(UUID employeeId) {
		EmployeeBusinessUnitRole role = new EmployeeBusinessUnitRole();
		String sql = "SELECT * FROM employee_business_unit_role WHERE (employee_id = ?)";
		SqlRowSet result = roleTemplate.queryForRowSet(sql, employeeId);
		if(result.next()) {
			role = mapResultToRole(result);
		} else {
			//come back for exception
			System.out.println("Field not found");
		}
		return role;
	}

	@Override
	public void saveRole(EmployeeBusinessUnitRole role, UUID employeeId) {
		String sql = "INSERT INTO employee_business_unit_role (employee_id, role_id, business_unit_id) " + 
				"VALUES ((SELECT employee_id FROM employee WHERE employee_id = ?),?,?)";
		roleTemplate.update(sql, employeeId,
				role.getRoleId(),
				role.getBusinessUnitId());
	}

	@Override
	public void updateRoleByEmployeeId(EmployeeBusinessUnitRole role, UUID employeeId) {
		String sql = "UPDATE employee_business_unit_role SET "
				+ "(employee_business_unit_role_id, employee_id, role_id, business_unit_id) "
				+"= (?, (SELECT employee_id FROM employee WHERE employee_id = ?),?,?)";
		roleTemplate.update(sql, 
				role.getEmployeeBusinessUnitRoleId(),
				employeeId,
				role.getRoleId(),
				role.getBusinessUnitId());
	}

	@Override
	public void deleteRoleByEmployeeId(UUID employeeId) {
		String sql = "DELETE FROM employee_business_unit_role WHERE (employee_id = ?)";
		roleTemplate.update(sql, employeeId);
	}
	
	private EmployeeBusinessUnitRole mapResultToRole(SqlRowSet result) {
		EmployeeBusinessUnitRole retrievedRole = new EmployeeBusinessUnitRole();
		retrievedRole.setEmployeeBusinessUnitRoleId((UUID) result.getObject("employee_business_unit_role_id"));
		retrievedRole.setEmployeeId((UUID) result.getObject("employee_id"));
		retrievedRole.setRoleId(result.getInt("role_id"));
		retrievedRole.setBusinessUnitId(result.getInt("business_unit_id"));
		return retrievedRole;
	}

}
