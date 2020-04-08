package com.techelevator.Perficient.controllers;

import java.sql.Timestamp;
import java.util.List;
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

import com.techelevator.Perficient.daos.FieldDAO;
import com.techelevator.Perficient.models.Field;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class FieldController {

	@Autowired
	FieldDAO fieldDAO;
	
	@GetMapping("/{employeeId}/fields")
	public List<Field> getAllFieldsByExmployeeId(@PathVariable UUID employeeId) {
		logTimestamp();
		List<Field> allFields = fieldDAO.getFieldByEmployeeId(employeeId);
		return allFields;
	}
	
	@GetMapping("/{employeeId}/skills/{skillId}/fields/{fieldId}")
	public Field getFieldByFieldId(@PathVariable UUID fieldId) {
		logTimestamp();
		Field field = fieldDAO.getFieldByFieldId(fieldId);
		return field;
	}

	@PostMapping("/fields")
	public void addField(@RequestBody Field field) {
		logTimestamp();
		fieldDAO.saveField(field);
	}
	
	@PutMapping("/{employeeId}/skills/{skillId}/fields/{fieldId}")
	public void updateFieldByFieldId(@RequestBody Field field, @PathVariable UUID fieldId) {
		logTimestamp();
		fieldDAO.updateFieldByFieldId(field, fieldId);
	}
	
	@DeleteMapping("/{employeeId}/fields/{fieldId}")
	public void deleteFieldByFieldId(@PathVariable UUID fieldId) {
		logTimestamp();
		fieldDAO.deleteFieldByFieldId(fieldId);;
	}
	
	@GetMapping("/fields/new")
	public Field getNewField() {
		logTimestamp();
		Field newField = fieldDAO.getNewField();
		return newField;
	}
	
	
	
	private static void logTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Request delivered from Perficient REST API at " + timestamp);
	}
}