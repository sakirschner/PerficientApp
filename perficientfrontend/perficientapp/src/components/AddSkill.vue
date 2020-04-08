<template>
  <div id="main">
      <br><br>
        <h2>Skill Continued</h2><br>
        <label>Experience:</label> 
        <input v-model='skill.experience' placeholder="# In Months"><br>
        <label>Summary:</label> 
        <input v-model='skill.summary'>
        <br>
        <button v-on:click='setId'>Add Employee</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            newEmployee: {},
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
        setId() {
            this.skill.employeeId = this.newEmployee.employeeId;
            this.skill.fieldId = this.newField.fieldId;
            this.addSkill(this.newEmployee.employeeId, this.newField.fieldId);
        },
        setNewEmployee() {
            fetch(`http://localhost:8080/Perficient/employees/new`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (newEmployee) => {
                    this.newEmployee = newEmployee;
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
            .then(
                window.alert("Employee Added")
            )
            .catch(
                (err) => {
                    console.error(err)
                }
           )
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        }
    },
    created() {
    this.setNewEmployee();
    this.setNewField();
    }
}
</script>

<style>

</style>