package com.techelevator.Perficient.daos;

import java.util.List;
import java.util.UUID;

import com.techelevator.Perficient.models.Skill;

public interface SkillDAO {

	public List<Skill> getAllSkillsByEmployeeId(UUID employeeId);
	
	public void addSkill(Skill skill, UUID employeeId, UUID fieldId);
	
	public Skill getSkillBySkillId(UUID skillId);
	
	public void updateSkillBySkillId(Skill skill, UUID skillId);
	
	public void deleteSkillsBySkillId(UUID skillId);
}
