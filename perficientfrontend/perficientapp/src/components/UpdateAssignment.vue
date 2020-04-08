<template>
  <div id="main">
      <br><br>
        Assignment<br><br>
            Manager Id: <input v-model='assignmentToEdit.superiorId'>
            <br>
            <router-link :to="{name: 'employees'}" target="_blank">
                View Ids
            </router-link>
            <br>
            <button v-on:click='updateAssignment(employeeId, assignmentToEdit.superiorId)'>Save</button><br><br>
            <button v-on:click="deleteAssignment(employeeId)">Delete Manager</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            employeeId: '',
            assignment: {},
            assignmentToEdit: {
                superiorId: '',
                subordinateId: '',
            }
        }
    },
    methods: {
        getAssignment(employeeId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/assignment`)
            .then (
                (response) => {
                    return response.json();
                }
            )
            .then (
                (assignment) => {
                    this.assignment = assignment;
                    this.assignmentToEdit.subordinateId = assignment.subordinateId;
                }
            )
            .catch (
                (err) => {
                    console.error(err)
                }
            )
        },
        updateAssignment(employeeId, managerId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/assignment/${managerId}`,
                {
                method: 'PUT',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(this.assignmentToEdit)
            })
            .then(
                window.alert('Role Updated')
            )
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        },
        deleteAssignment(employeeId) {
           fetch(`http://localhost:8080/Perficient/employees/${employeeId}/assignment`, 
                {
                    method: 'DELETE'
                })
            .catch(
                (err) => {
                    console.error(err)
                }
            )
            this.$router.push({name: 'employee-details', params: {employeeId: employeeId}});
        }
    },
    created() {
        this.employeeId = this.$route.params.employeeId;
        this.getAssignment(this.$route.params.employeeId);
    }
}
</script>

<style>

</style>