package com.techelevator.Perficient.models;

import java.util.UUID;

public class Field {
	
	private UUID fieldId;
	private String name;
	private String type;
	
	public UUID getFieldId() {
		return fieldId;
	}
	
	public void setFieldId(UUID fieldId) {
		this.fieldId = fieldId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
