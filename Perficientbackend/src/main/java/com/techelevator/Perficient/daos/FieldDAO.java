package com.techelevator.Perficient.daos;

import java.util.List;
import java.util.UUID;

import com.techelevator.Perficient.models.Field;

public interface FieldDAO {
	
	public List<Field> getFieldByEmployeeId(UUID employeeId);

	public Field getFieldByFieldId(UUID fieldId);
	
	public void saveField(Field field);
	
	public void updateFieldByFieldId(Field field, UUID fieldId);
	
	public void deleteFieldByFieldId(UUID fieldId);
	
	public Field getNewField();
}
