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
import com.techelevator.Perficient.daos.SkillDAO;
import com.techelevator.Perficient.models.Field;
import com.techelevator.Perficient.models.Skill;

@RestController
@RequestMapping("/employees/{employeeId}")
@CrossOrigin
public class SkillController {

	@Autowired
	SkillDAO skillDAO;
	@Autowired
	FieldDAO fieldDAO;
	
	@GetMapping("/skills")
	public List<Skill> getEmployeeSkillsByEmployeeId(@PathVariable UUID employeeId) {
		logTimestamp();
		List<Skill> allSkills = skillDAO.getAllSkillsByEmployeeId(employeeId);
		for(Skill skill: allSkills) {
			UUID fieldId = skill.getFieldId();
			Field field = fieldDAO.getFieldByFieldId(fieldId);
			skill.setField(field);
		}
		return allSkills;
	}

	@PostMapping("/skills/{fieldId}")
	public void addEmployeeSkillByEmployeeId(@RequestBody Skill skill,@PathVariable UUID employeeId, @PathVariable UUID fieldId) {
		logTimestamp();
		skillDAO.addSkill(skill, employeeId, fieldId);
	}
	
	@GetMapping("/skills/{skillId}")
	public Skill getEmployeeSkillBySkillId(@PathVariable UUID skillId) {
		logTimestamp();
		Skill skill = skillDAO.getSkillBySkillId(skillId);
		UUID fieldId = skill.getFieldId();
		Field field = fieldDAO.getFieldByFieldId(fieldId);
		skill.setField(field);
		return skill;
	}
	
	@PutMapping("/skills/{skillId}")
	public void updateEmployeeSkillBySkillId(@RequestBody Skill skill, @PathVariable UUID skillId) {
		logTimestamp();
		skillDAO.updateSkillBySkillId(skill, skillId);
	}
	
	@DeleteMapping("/skills/{skillId}")
	public void deleteSkillBySkillId(@PathVariable UUID skillId) {
		logTimestamp();
		skillDAO.deleteSkillsBySkillId(skillId);
	}
	
	private static void logTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Request delivered from Perficient REST API at " + timestamp);
	}
}
