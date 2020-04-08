<template>
  <div id="main">
      <br><br>
        Assignment<br><br>
            Manager Id: <input v-model='assignment.superiorId'>
            <br>
            <router-link :to="{name: 'employees'}" target="_blank">
                View Ids
            </router-link>
            <br>
        <button v-on:click='setRoleEmployeeId'>Next</button>
  </div>
</template>

<script>
export default {
    data() {
        return {
            newEmployee: {},
            assignment: {
                superiorId: '',
                subordinateId: '',
            }
        }
    },
    methods: {
        setRoleEmployeeId() {
            this.assignment.subordinateId = this.newEmployee.employeeId;
            this.addAssignment(this.newEmployee.employeeId, this.assignment.superiorId);
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
        addAssignment(employeeId, managerId) {
            fetch(`http://localhost:8080/Perficient/employees/${employeeId}/assignment/${managerId}`,
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                },
                body: JSON.stringify(this.assignment)
            })
            .catch(
                (err) => {
                    console.error(err)
                }
           )
            this.$router.push({ name: 'add-address' });
        }
    },
    created() {
    this.setNewEmployee();
    }
}
</script>

<style>

</style>