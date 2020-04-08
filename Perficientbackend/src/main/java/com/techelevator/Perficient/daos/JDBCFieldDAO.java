package com.techelevator.Perficient.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Perficient.models.Employee;
import com.techelevator.Perficient.models.Field;
import com.techelevator.Perficient.models.Skill;

@Component
public class JDBCFieldDAO implements FieldDAO {
	
	private JdbcTemplate fieldTemplate;
	
	public JDBCFieldDAO(DataSource datasource) {
		fieldTemplate = new JdbcTemplate(datasource); 
	}

	@Override
	public List<Field> getFieldByEmployeeId(UUID employeeId) {
		List<Field> allFields = new ArrayList<>();
		String sql = "SELECT * FROM field "
				+ "JOIN skill ON skill.field_id = field.field_id "
				+ "WHERE (employee_id = ?)";
		SqlRowSet results = fieldTemplate.queryForRowSet(sql, employeeId);
		while(results.next()) {
			allFields.add(mapResultToField(results));
		}
		return allFields;
	}
	
	@Override
	public Field getFieldByFieldId(UUID fieldId) {
		Field retrievedField = new Field();
		String sql = "SELECT * FROM field WHERE (field_id = ?)";
		SqlRowSet result = fieldTemplate.queryForRowSet(sql, fieldId);
		if(result.next()) {
			retrievedField = mapResultToField(result);
		} else {
			//come back for exception
			System.out.println("Field not found");
		}
		return retrievedField;
	}

	@Override
	public void saveField(Field field) {
		String sql = "INSERT INTO field (name, type) VALUES (?,?)";
		fieldTemplate.update(sql, field.getName(), field.getType());
	}
	
	@Override
	public void updateFieldByFieldId(Field field, UUID fieldId) {
		String sql = "UPDATE field SET (name, type) "
				+ "= (?,?) WHERE (field_id = ?)";
		fieldTemplate.update(sql,
				field.getName(),
				field.getType(),
				fieldId);
	}

	@Override
	public void deleteFieldByFieldId(UUID fieldId) {
		String sql = "DELETE FROM field WHERE (field_id = ?)";
		fieldTemplate.update(sql, fieldId);
	}
	
	@Override
	public Field getNewField() {
		Field retrievedField = new Field();
		String sql = "SELECT * FROM field " + 
				"ORDER BY created_at DESC " + 
				"LIMIT 1";
		SqlRowSet result = fieldTemplate.queryForRowSet(sql);
		if(result.next()) {
			retrievedField = mapResultToField(result);
		} else {
			//come back for exception
			System.out.println("Field not found");
		}
		return retrievedField;
	}
	
	private Field mapResultToField(SqlRowSet result) {
		Field retrievedField = new Field();
		retrievedField.setFieldId((UUID)result.getObject("field_id"));
		retrievedField.setName(result.getString("name"));
		retrievedField.setType(result.getString("type"));
		return retrievedField;
	}

}
