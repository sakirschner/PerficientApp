<template>
  <div id="main">
        <br><br>
        <h2>Update Skill</h2>
        <br>
        <label>Skill:</label> 
        <input v-model="field.name"><br>
        <label>Type:</label>
        <input v-model="field.type"><br>
        <label>Experience:</label>
        <input v-model="skill.experience"><br>
        <label>Summary:</label> <input v-model="skill.summary"><br>
        <button v-on:click="setSkillToEdit(employeeId, skill.skillId, field.fieldId)">Save</button>
        <button v-on:click="goBack(employeeId)">Cancel</button>
        <button v-on:click="deleteSkills(employeeId, skill.skillId, field.fieldId)" class="delete">Delete skills</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            employeeId: {},
            skill: {},
            field: {},
            skillToEdit: {
                skillId: this.$route.params.skillId,
                employeeId: this.$route.params.employeeId,
                fieldId: this.$route.params.fieldId,
                experience: '',
                summary: ''
            }
        }
    },
    methods: {
        setSkillToEdit(employeeId, skillId, fieldId) {
            this.skillToEdit.experience = this.skill.experience;
            this.skillToEdit.summary = this.skill.summary;
            this.updateSkill(employeeId, skillId, fieldId);
        },
        getSkill(employeeId, skillId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/skills/${skillId}`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (skill) => {
                    this.skill = skill;
                }
            )
            .catch (
                (err) => {
                    console.error(err)
                }
            )
        },
        getField(employeeId, skillId, fieldId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/skills/${skillId}/fields/${fieldId}`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (field) => {
                    this.field = field;
                }
            )
            .catch (
                (err) => {
                    console.error(err)
                }
            )
        },
        updateSkill(employeeId, skillId, fieldId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/skills/${skillId}`,
                {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.skillToEdit)
            })
            .then(
                window.alert('Skill Updated')
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.updateField(employeeId, skillId, fieldId);
        },
        updateField(employeeId, skillId, fieldId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/skills/${skillId}/fields/${fieldId}`,
                {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.field)
            })
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.goBack(employeeId);
        },
        deleteSkills(employeeId, skillId, fieldId) {
           fetch(`http://localhost:8080/Perficient/employees/${employeeId}/skills/${skillId}`, 
                {
                    method: 'DELETE'
                })
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.deleteField(employeeId, fieldId)
        },
        deleteField(employeeId, fieldId) {
           fetch(`http://localhost:8080/Perficient/employees/${employeeId}/fields/${fieldId}`, 
                {
                    method: 'DELETE'
                })
            .then(
                window.alert("Skill Deleted")
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.goBack(employeeId);
        },
        goBack(employeeId) {
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        }
    },
    created() {
        this.employeeId = this.$route.params.employeeId;
        this.getSkill(this.$route.params.employeeId, this.$route.params.skillId);
        this.getField(this.$route.params.employeeId, this.$route.params.skillId, this.$route.params.fieldId);
    }
}
</script>

<style>

</style>