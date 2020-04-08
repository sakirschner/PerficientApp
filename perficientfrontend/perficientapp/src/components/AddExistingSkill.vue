<template>
  <div id="main">
      <br><br>
        <h2>Skill Continued</h2><br>
        <label>Experience:</label>
        <input v-model='skill.experience' placeholder="# in Months"><br>
        <label>Summary:</label> 
        <input v-model='skill.summary'>
        <br>
        <button v-on:click='setSkillEmployeeId'>Save</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            employee: {},
            newField: {},
            skill: {
                employeeId: '',
                fieldId: '',
                experience: '',
                summary: ''
            }
        }
    },
    methods: {
        setSkillEmployeeId() {
            this.skill.employeeId = this.employee.employeeId;
            this.skill.fieldId = this.newField.fieldId;
            this.addSkill(this.employee.employeeId, this.newField.fieldId);
        },
        setEmployee(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (employee) => {
                    this.employee = employee;
                }
            )
            .catch (
                (err) => {
                    console.error(err);
                }
            )
        },
        setNewField() {
            fetch(`http://localhost:8080/Perficient/employees/fields/new`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (newField) => {
                    this.newField = newField;
                }
            )
            .catch (
                (err) => {
                    console.error(err);
                }
            )
        },
        addSkill(employeeId, fieldId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/skills/${fieldId}`,
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                },
                body: JSON.stringify(this.skill)
            })
            .catch(
                (err) => {
                    console.error(err)
                }
           )
            .then(
                window.alert("Skill Added")
            )
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        }
    },
    created() {
    this.setEmployee(this.$route.params.employeeId);
    this.setNewField();
    }
}
</script>

<style>

</style>