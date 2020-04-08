package com.techelevator.Perficient.daos;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Perficient.models.AssignedTo;

@Component
public class JDBCAssignedToDAO implements AssignedToDAO {
	
	private JdbcTemplate assignmentTemplate;
	
	public JDBCAssignedToDAO(DataSource datasource) {
		assignmentTemplate = new JdbcTemplate(datasource); 
	}

	@Override
	public AssignedTo getManagerByEmployeeId(UUID employeeId) {
		AssignedTo assignment = new AssignedTo();
		String sql = "SELECT * FROM assigned_to JOIN employee ON "
				+ "(employee.employee_id = assigned_to.superior_id) " 
				+ "WHERE (subordinate_id = ?)";
		SqlRowSet result = assignmentTemplate.queryForRowSet(sql, employeeId);
		if(result.next()) {
			assignment.setAssignedToId((UUID) result.getObject("assigned_to_id"));
			assignment.setSuperiorId((UUID) result.getObject("superior_id"));
			assignment.setSubordinateId((UUID) result.getObject("subordinate_id"));
			assignment.setManagerFirstName(result.getString("first_name"));
			assignment.setManagerLastName(result.getString("last_name"));
		} else {
			//come back for exception
			System.out.println("Assignment not found");
		}
		return assignment;
	}

	@Override
	public void saveAssignment(UUID managerId, UUID employeeId) {
		String sql = "INSERT INTO assigned_to (superior_id, subordinate_id) " + 
				"VALUES ((SELECT employee_id FROM employee WHERE employee_id = ?), (SELECT employee_id FROM employee WHERE employee_id = ?))";
		assignmentTemplate.update(sql, managerId, employeeId);
	}

	@Override
	public void updateAssignment(UUID managerId, UUID employeeId, AssignedTo assignment) {
		String sql = "UPDATE assigned_to SET "
				+ "(superior_id, subordinate_id) "
				+"= (?, ?) "
				+"WHERE assigned_to_id = ?";
		assignmentTemplate.update(sql, managerId, employeeId, assignment.getAssignedToId());
	}

	@Override
	public void deleteAssignment(UUID employeeId) {
		String sql = "DELETE FROM assigned_to WHERE (subordinate_id = ?)";
		assignmentTemplate.update(sql, employeeId);
	}

}
