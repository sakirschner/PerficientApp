package com.techelevator.Perficient.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Perficient.models.Field;
import com.techelevator.Perficient.models.Skill;

@Component
public class JDBCSkillDAO implements SkillDAO {

	private JdbcTemplate skillTemplate;
	
	//creates database connection
	public JDBCSkillDAO(DataSource datasource) {
		skillTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<Skill> getAllSkillsByEmployeeId(UUID employeeId) {
		List<Skill> allSkills = new ArrayList<>();
		String sql = "SELECT * FROM skill "
				+ "WHERE (employee_id = ?)";
		SqlRowSet results = skillTemplate.queryForRowSet(sql, employeeId);
		while(results.next()) {
			allSkills.add(mapResultToSkill(results));
		}
		return allSkills;
	}

	@Override
	public void addSkill(Skill skill, UUID employeeId, UUID fieldId) {
		String sql = "INSERT INTO skill (employee_id, field_id, experience, summary) VALUES "
				+ "((SELECT employee_id FROM employee WHERE employee_id = ?), "
				+ "(SELECT field_id FROM field WHERE field_id = ?), ?,?)";
		skillTemplate.update(sql, employeeId, 
				fieldId,
				skill.getExperience(),
				skill.getSummary());
	}

	@Override
	public Skill getSkillBySkillId(UUID skillId) {
		Skill retrievedSkill = new Skill();
		String sql = "SELECT * FROM skill WHERE (skill_id = ?)";
		SqlRowSet result = skillTemplate.queryForRowSet(sql, skillId);
		if(result.next()) {
			retrievedSkill.setSkillId((UUID)result.getObject("skill_id"));
			retrievedSkill.setEmployeeId((UUID)result.getObject("employee_id"));
			retrievedSkill.setFieldId((UUID)result.getObject("field_id"));
			retrievedSkill.setExperience(result.getInt("experience"));
			retrievedSkill.setSummary(result.getString("summary"));
		} else {
			//come back for exception
			System.out.println("Skill not found");
		}
		return retrievedSkill;
	}

	@Override
	public void updateSkillBySkillId(Skill skill, UUID skillId) {
		String sql = "UPDATE skill SET (employee_id, field_id, experience, summary) "
				+ "= (?,?,?,?) WHERE (skill_id = ?)";
		skillTemplate.update(sql,
				skill.getEmployeeId(),
				skill.getFieldId(),
				skill.getExperience(),
				skill.getSummary(),
				skillId);
	}

	@Override
	public void deleteSkillsBySkillId(UUID skillId) {
		String sql = "DELETE FROM skill WHERE (skill_id = ?)";
		skillTemplate.update(sql, skillId);

	}
	
	private Skill mapResultToSkill(SqlRowSet result) {
		Skill retrievedSkill = new Skill();
		Field retrievedField = new Field();
		retrievedSkill.setSkillId((UUID)result.getObject("skill_id"));
		retrievedSkill.setEmployeeId((UUID)result.getObject("employee_id"));
		retrievedSkill.setFieldId((UUID)result.getObject("field_id"));
		retrievedSkill.setExperience(result.getInt("experience"));
		retrievedSkill.setSummary(result.getString("summary"));
		retrievedSkill.setField(retrievedField);
		return retrievedSkill;
	}

}
