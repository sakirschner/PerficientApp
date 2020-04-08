package com.techelevator.Perficient.models;

import java.util.UUID;

public class Skill {

	private UUID skillId;
	private UUID employeeId;
	private UUID fieldId;
	private int experience;
	private String summary;
	private Field field;
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public UUID getSkillId() {
		return skillId;
	}
	
	public void setSkillId(UUID skillId) {
		this.skillId = skillId;
	}
	
	public UUID getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public UUID getFieldId() {
		return fieldId;
	}

	public void setFieldId(UUID fieldId) {
		this.fieldId = fieldId;
	}
}
